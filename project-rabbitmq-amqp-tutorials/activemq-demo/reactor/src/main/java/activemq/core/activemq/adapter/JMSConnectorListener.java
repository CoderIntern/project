package activemq.core.activemq.adapter;

import activemq.core.activemq.jms.JMSConnector;

/**
 * <p> Date             :2018/3/31 </p>
 * <p> Module           : </p>
 * <p> Description      :ActiveMQ执行发送前后的监听类 </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public interface JMSConnectorListener {

    /**
     * ActiveMQ执行发送之前的操作
     * @param event
     */
    void beforeSend(JMSConnectorEvent event);

    /**
     * ActiveMQ执行发送之后的操作
     * @param event
     */
    void afterSend(JMSConnectorEvent event);

    /**
     * commit 之后的操作
     * @param event
     */
    void afterCommit(JMSConnectorEvent event);

    /**
     * 获取输出的XML内容
     * @param jmsConnector
     */
    void setJMSConnector(JMSConnector jmsConnector);
}
