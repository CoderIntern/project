package rabbitmqdemo.rabbbittest.config;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> Date             :2018/3/22 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class Main {

    static final String exchangeName = "testblock";
    static final String routingKey = "testblock";
    static final String queueName = "testblock";

    private static int producterConnection_size = 1; //消息生产者连接数
    private static int consumerConnection_size = 1; //消费者连接数
    private static final int consumer_size = 1;//每个消费者连接里面开启的consumer数量
    private static int qos = 10; //Qos设置
    private static long sleep_time = 0; //模拟每条消息的处理时间
    private static boolean autoAck = true; //是否默认Ack

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final AtomicLong count = new AtomicLong(10000000000L);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        //启动监控程序
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                long c = count.get();
                while (c != 0) {
                    try {
                        Thread.sleep(1000);
                        long c1 = count.get();
                        logger.debug("每秒消费为:{}Qps", c - c1);
                        c = c1;
                    } catch (Exception e) {
                    }
                }
            }
        });
        t.start();
        //启动
        for (int i = 0; i < producterConnection_size; i++) {
            Connection conn1 = factory.newConnection();
            Thread t1 = producter(conn1, count.get());
            t1.start();
        }
        //启动consumer
        for (int i = 0; i < consumerConnection_size; i++) {
            Connection conn1 = factory.newConnection();
            Thread t2 = consumer(conn1, count);
            t2.start();
        }
    }

    public static Thread consumer(final Connection conn, final AtomicLong count) throws Exception {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                logger.debug("start consumer");
                try {
                    final CountDownLatch cdl = new CountDownLatch(1000);
                    for (int i = 0; i < consumer_size; i++) {
                        final Channel channel = conn.createChannel();
                        channel.basicQos(0, qos, false);
                        Consumer consumer = new DefaultConsumer(channel) {
                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                                if (count.decrementAndGet() == 0) {
                                    channel.basicCancel(consumerTag);
                                    cdl.countDown();
                                    try {
                                        channel.close();
                                    } catch (TimeoutException e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    Thread.sleep(sleep_time);
                                } catch (InterruptedException e) {
                                }
                                if (!autoAck) {
                                    getChannel().basicAck(envelope.getDeliveryTag(), true);
                                }
                            }
                        };
                        String consumerTag = channel.basicConsume(queueName, autoAck, "testConsumer" + i, consumer);
                        logger.debug("consumerTag is {}", consumerTag);
                    }
                    cdl.await();
                } catch (Exception e) {
                }
            }
        });
    }


    public static Thread producter(final Connection conn, final long count) throws Exception {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                logger.debug("start send Message");
                try {
                    Channel channel = conn.createChannel();
                    channel.exchangeDeclare(exchangeName, "direct", true);
                    channel.queueDeclare(queueName, true, false, false, null);
                    channel.queueBind(queueName, exchangeName, routingKey);
                    BasicProperties properties = new BasicProperties.Builder().deliveryMode(2).build();
                    for (long i = 0; i < count; i++) {
                        byte[] messageBodyBytes = ("{\"merchantsId\":13}").getBytes();
                        channel.basicPublish(exchangeName, routingKey, properties, messageBodyBytes);
//            logger.debug("add message {}",i);
                    }
                    channel.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}