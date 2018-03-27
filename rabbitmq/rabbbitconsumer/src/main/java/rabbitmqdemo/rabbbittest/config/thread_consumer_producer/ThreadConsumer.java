package rabbitmqdemo.rabbbittest.config.thread_consumer_producer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> Date             :2018/3/23 </p>
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
public class ThreadConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ThreadConsumer.class);

    public Thread consumer(final Connection connection, final AtomicLong count) {
        return new Thread(new Runnable() {
            @Override
            public void run()  {
                logger.debug("start consumer:");
                try {
                    final CountDownLatch cdl = new CountDownLatch(1000);
                    for (int i = 0; i < 1; i++) {
                        final Channel channel = connection.createChannel();
                        channel.basicQos(0, 10, false);
                        Consumer consumer = new DefaultConsumer(channel){
                            @Override
                            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                                if (count.decrementAndGet() == 0) {
                                    channel.basicCancel(consumerTag);
                                    cdl.countDown();
                                    try {
                                        channel.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                try {
                                    Thread.sleep(0L);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (false) {
                                    getChannel().basicAck(envelope.getDeliveryTag(), true);
                                }
                            }
                        };
                        String consumerTag = channel.basicConsume("testblock", true, "testConsumer" + i, consumer);
                        logger.debug("consumerTag is {}", consumerTag);
                    }
                    cdl.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
