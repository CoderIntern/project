/*
package rabbitmqdemo.rabbbittest.config;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

*/
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
 *//*

public class Recv {
    private final static String QUEUE_NAME = "hello";


    public static void main(String[] argv) throws Exception {
        boolean autoAck = true;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


//        channel.basicAck(5000L, true);

//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        int batchSize = 5000;
        List<GetResponse> responses = new ArrayList<>(batchSize);
        long tag =0;
        while (true) {
            GetResponse response1 = channel.basicGet(QUEUE_NAME, autoAck);

            if (response1 == null) {
                System.out.println("NULL ......");
                System.exit(0);
                break;
            }else {
                while (responses.size() < batchSize) {
                    GetResponse response = channel.basicGet(QUEUE_NAME, autoAck);
                    if (response == null) {
                        break;
                    }
                    if (responses.size() >= batchSize)
                        responses.clear();
                    responses.add(response);
                    tag = response.getEnvelope().getDeliveryTag();
                }
            }
        }

        */
/*while (true) {
            GetResponse response = channel.basicGet(QUEUE_NAME, autoAck);

            if (response == null) {
                System.out.println("NULL ......");
                System.exit(0);
                break;
            }else {
                AMQP.BasicProperties props = response.getProps();
                byte[] body = response.getBody();
                String str = new String(body);
                System.out.println(str);
                long deliveryTag = response.getEnvelope().getDeliveryTag();
            }
        }*//*


        */
/*ThreadConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);*//*

    }
}
*/
