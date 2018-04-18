package com.daily.dailytest.core.utils;

import com.daily.dailytest.core.exception.LangException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

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
public class LangUtil {

    private MessageSource messageSource;

    //日志
    private final Logger logger = LoggerFactory.getLogger(LangUtil.class);

    /**
     * 根据code获取对应的信息
     * @param code
     * @return
     */
    public String getCodeString(String code) {
        return getCodeString(code, null);
    }

    public String getCodeString(String code, String[] params) {
        //默认中国
        Locale locale = new Locale("zh", "CN");
        String result;
        try {
            result = messageSource.getMessage(code.toString(), params, locale);
        } catch (NoSuchMessageException e) {
            logger.debug("langUtil analyze error code => [{}] params = [{}]", code, params);
            throw new LangException(e);
        }
        if (result.equals(code)) {
            return "";
        }
        return result;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
