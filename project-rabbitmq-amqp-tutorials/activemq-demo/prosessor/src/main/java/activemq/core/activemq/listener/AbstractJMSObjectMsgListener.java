package activemq.core.activemq.listener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;
import java.io.Serializable;

/**
 * 包装ObjectMessage监听器父类
 * <p>
 * spring中jms消息接受容器中的监听抽象类，用于实现消息接受后的处理。需要实现dealWithObjMsg抽象方法。
 *
 * @author 亢中港
 * @version [v1.0, 12-2-10 下午5:21]
 */
public abstract class AbstractJMSObjectMsgListener implements SessionAwareMessageListener, ExceptionListener {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJMSObjectMsgListener.class);

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        try {
            dealWithMsg(message);
        } catch (Exception e) {
            logger.error("onMessage error!", e);
            throw new RuntimeException("onMessage RuntimeException!!");
        }
    }

    /**
     * 异常处理方法
     *
     * @param e JMSException
     */
    public void onException(JMSException e) {
        logger.error("occured JMSException:", e);
    }

    /**
     * 具体处理接收到的消息，需要在子类中进行实现
     *
     * @param msg 接收到的消息
     */
    private void dealWithMsg(Message msg) throws Exception {

        logger.debug("mq deal msg queue ===> [{}]", ((ActiveMQObjectMessage) msg).getMessage().getDestination());

        Serializable serializable = getMessageBean(msg);
        doService(serializable);
    }

    /**
     * 获取序列化消息
     *
     * @param message 消息对象
     * @return java.io.Serializable
     * @throws JMSException
     */
    private Serializable getMessageBean(Message message) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        return objectMessage.getObject();
    }

    /**
     * 获取数据
     */
    protected abstract void doService(Serializable serializable) throws Exception;

    /**
     * custId 计算规则：取custId的最后两位
     * @param custId 客户标识
     * @return String
     */
    protected String custIdIndex(String custId) {
        return custId.substring(custId.length() - 2);
    }
}
