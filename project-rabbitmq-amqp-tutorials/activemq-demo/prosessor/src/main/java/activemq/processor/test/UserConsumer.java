package activemq.processor.test;

import activemq.core.activemq.listener.AbstractJMSObjectMsgListener;
import activemq.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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
@Component("userConsumer")
public class UserConsumer extends AbstractJMSObjectMsgListener {

    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @Override
    protected void doService(Serializable serializable) throws Exception {
        logger.info(serializable.toString());
        Person person = (Person) serializable;
    }
}
