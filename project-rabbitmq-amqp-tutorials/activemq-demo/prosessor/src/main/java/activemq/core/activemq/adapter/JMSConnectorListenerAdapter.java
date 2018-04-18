package activemq.core.activemq.adapter;

import activemq.core.activemq.jms.JMSConnector;

/**
 * active mq jsm connector listener adapter 统一类
 * <p> Date             :2017/9/20 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author kangzhonggang
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class JMSConnectorListenerAdapter implements JMSConnectorListener {

    @Override
    public void beforeSend(JMSConnectorEvent e) {
    }

    @Override
    public void afterSend(JMSConnectorEvent e) {
    }

    @Override
    public void afterCommit(JMSConnectorEvent e) {

    }

    @Override
    public void setJMSConnector(JMSConnector jmsConnector) {
    }
}
