package activemq.core.activemq.adapter;

/**
 * <p> Date             :2018/3/31 </p>
 * <p> Module           : </p>
 * <p> Description      : ActiveMQ操作类型枚举类</p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class JMSConnectorEvent {

    /**
     * 呈现名称
     */
    public String threadName;

    /**
     * 类型枚举
     */
    public Type type;

    /**
     * 事件
     */
    public Object event;

    /**
     * 构造方法
     * @param threadName
     * @param type
     * @param event
     */
    public JMSConnectorEvent(String threadName, Type type, Object event) {
        this.threadName = threadName;
        this.type = type;
        this.event = event;
    }

    /**
     * ActiveMQ操作类型类
     * SEND:ActiveMQ发送
     * DELAY_SEND:ActiveMQ延时发送
     * COMMIT:提交
     */
    public static enum Type {
        SEND, DELAY_SEND, COMMIT
    }

}
