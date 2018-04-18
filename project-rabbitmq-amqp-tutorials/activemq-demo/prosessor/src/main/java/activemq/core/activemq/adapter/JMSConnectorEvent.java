package activemq.core.activemq.adapter;

/**
 * 支持active mq的操作枚举类
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
public class JMSConnectorEvent {
    public String threadName;
    public Type type;
    public Object event;

    public JMSConnectorEvent(String threadName, Type type, Object event) {
        this.threadName = threadName;
        this.type = type;
        this.event = event;
    }

    public enum Type {
        SEND, DELAY_SEND, COMMIT
    }
}
