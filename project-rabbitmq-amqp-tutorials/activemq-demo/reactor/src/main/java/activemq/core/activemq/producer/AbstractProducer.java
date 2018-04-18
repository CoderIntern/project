package activemq.core.activemq.producer;

import activemq.core.activemq.jms.JMSConnector;
import activemq.core.exception.MQException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * <p> Date             :2018/3/31 </p>
 * <p> Module           : </p>
 * <p> Description      : 生产者继承使用</p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public abstract class AbstractProducer {

    /**
     * JMS连接
     */
    private JMSConnector jmsConnector;

    /**
     * 生产者配置
     */
    private ProducerConfigBean producerConfigBean;

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractProducer.class);

    /**
     * 发型消息到ActiveMQ
     * @param object
     */
    public void sendMsg(Serializable object) {
        if (null == this.producerConfigBean) {
            this.producerConfigBean = this.InitProducer();
            this.initJmsConnector();
        }
        this.jmsConnector.send(object);
    }

    /**
     * 初始化 JmsConnector
     */
    private void initJmsConnector() {
        if (null != this.jmsConnector) {
            return;
        }
        this.jmsConnector = new JMSConnector();
        this.jmsConnector.setDestinationQueueName(this.producerConfigBean.getDestinationQueueName());
        this.jmsConnector.setJmsTemplate(this.producerConfigBean.getJmsTemplate());
        this.jmsConnector.setBatchSize(this.producerConfigBean.getBatchSize());
        this.jmsConnector.setIntervalTimeInMillisOfCommit(this.producerConfigBean.getIntervalTimeInMillisOfCommit());
        this.jmsConnector.setThreadName(this.producerConfigBean.getThreadName());
        try {
            this.jmsConnector.afterPropertiesSet();
        } catch (Exception e) {
            logger.info("JmsConnector run afterPropertiesSet Error! ");
            throw new MQException(e);
        }

    }
    protected abstract ProducerConfigBean InitProducer();
}