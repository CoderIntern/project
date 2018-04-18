package consumer.recv;

import consumer.beans.Person;
import consumer.utils.ProtoStuffSerializerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p> Date             :2018/4/13 </p>
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
@Component
public class RabbitmqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConsumer.class);

    @RabbitListener(queues = {"fanout.A"})
    public void process(byte[] bytes) {
        Person deserialize = ProtoStuffSerializerUtil.deserialize(bytes, Person.class);
        logger.info(deserialize.toString());
    }

    @RabbitListener(queues = {"fanout.B"})
    public void process1(byte[] bytes) {
        Person deserialize = ProtoStuffSerializerUtil.deserialize(bytes, Person.class);
        logger.info(deserialize.toString());
    }
}
