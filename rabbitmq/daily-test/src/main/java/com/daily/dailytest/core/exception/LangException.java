package com.daily.dailytest.core.exception;

/**
 * <p> Date             :2018/1/17 </p>
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
public class LangException extends RuntimeException {
    public LangException() {
    }

    public LangException(String message) {
        super(message);
    }

    public LangException(String message, Throwable cause) {
        super(message, cause);
    }

    public LangException(Throwable cause) {
        super(cause);
    }

    public LangException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(super.getMessage());
        if (this.getCause() != null) {
            message.append("; Lang Exception is ").append(this.getCause());
        }
        return message.toString();
    }
}
