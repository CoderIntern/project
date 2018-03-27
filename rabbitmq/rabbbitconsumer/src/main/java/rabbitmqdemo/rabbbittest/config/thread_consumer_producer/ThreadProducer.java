package rabbitmqdemo.rabbbittest.config.thread_consumer_producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.AMQP.BasicProperties;

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
public class ThreadProducer {
    private static final Logger logger = LoggerFactory.getLogger(ThreadProducer.class);
    public Thread producer(final Connection connection, final long count) throws Exception {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                logger.debug("start send message: ");
                try {
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare("testblock", "direct", true);
                    channel.queueDeclare("testblock", true, false, false, null);
                    channel.queueBind("testblock", "testblock", "testblock");
                    BasicProperties properties = new BasicProperties.Builder().deliveryMode(2).build();
                    for (long i = 0; i < 10000000000L; i ++) {
                        byte[] messageBodyBytes = ("{\"merchantsId\":13}").getBytes();
                        channel.basicPublish("testblock", "testblock", properties, messageBodyBytes);
                    }
                    channel.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
