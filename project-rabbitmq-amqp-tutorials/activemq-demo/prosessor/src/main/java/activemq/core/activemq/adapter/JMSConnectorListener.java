package activemq.core.activemq.adapter;

import activemq.core.activemq.jms.JMSConnector;

/**
 * active mq 执行发送时前后监听类
 * <p> Date             :2017/9/20 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author kangzhonggang
 * @version 1.0
 *          <p>--------------------------------------------------------------</p>
 *          <p>修改历史</p>
 *          <p>    序号    日期    修改人    修改原因    </p>
 *          <p>    1                                     </p>
 */
public interface JMSConnectorListener {

    /**
     * send 执行前
     * <p> 描述           :</p>
     * <p> 备注           :</p>
     *
     * @param e 节点名称
     */
    void beforeSend(JMSConnectorEvent e);

    /**
     * send 执行之后
     * <p> 描述           :</p>
     * <p> 备注           :</p>
     *
     * @param e 节点名称
     */
    void afterSend(JMSConnectorEvent e);

    /**
     * commit 之前
     * <p> 描述           :</p>
     * <p> 备注           :</p>
     *
     * @param e 节点名称
     */
    void afterCommit(JMSConnectorEvent e);

    /**
     * 获取输出的XML内容
     * <p> 描述           :</p>
     * <p> 备注           :</p>
     *
     * @param jmsConnector jmsConnector名称
     */
    void setJMSConnector(JMSConnector jmsConnector);
}
