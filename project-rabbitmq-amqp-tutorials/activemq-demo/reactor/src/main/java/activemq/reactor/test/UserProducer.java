package activemq.reactor.test;

import activemq.core.activemq.producer.AbstractProducer;
import activemq.core.activemq.producer.ProducerConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * <p> Date             :2018/3/31 </p>
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
@Service("userProducer")
public class UserProducer extends AbstractProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 队列名
     */
    private static final String QUEUE_NAME = "user-queue";

    /**
     * 线程名
     */
    private static final String THREAD_NAME = "user-thread";

    @Override
    protected ProducerConfigBean InitProducer() {
        return new ProducerConfigBean(QUEUE_NAME, THREAD_NAME,jmsTemplate);
    }
}
