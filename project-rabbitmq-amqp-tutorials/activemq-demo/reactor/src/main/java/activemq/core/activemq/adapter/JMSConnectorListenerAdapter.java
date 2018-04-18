package activemq.core.activemq.adapter;

import activemq.core.activemq.jms.JMSConnector;

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
public class JMSConnectorListenerAdapter implements JMSConnectorListener {
    @Override
    public void beforeSend(JMSConnectorEvent event) {

    }

    @Override
    public void afterSend(JMSConnectorEvent event) {

    }

    @Override
    public void afterCommit(JMSConnectorEvent event) {

    }

    @Override
    public void setJMSConnector(JMSConnector jmsConnector) {

    }
}
