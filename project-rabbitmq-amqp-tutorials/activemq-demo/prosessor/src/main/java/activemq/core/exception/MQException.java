package activemq.core.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

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
public class MQException extends RuntimeException {

    private static final long serialVersionUID = -6572074930377920603L;

    private Throwable cause;

    public MQException(){
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
    }

    public MQException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    @Override
    public synchronized Throwable getCause() {
        return null == this.cause ? null :this.cause;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        if (cause != null) {
            message.append("; MQ Exception is ").append(cause);
        }
        return message.toString();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        if (null == getCause()) {
            super.printStackTrace(s);
        } else {
            s.println(this);
            getCause().printStackTrace(s);
        }
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        if (null == getCause()) {
            super.printStackTrace(s);
        } else {
            s.println(this);
            getCause().printStackTrace(s);
        }
    }
}
