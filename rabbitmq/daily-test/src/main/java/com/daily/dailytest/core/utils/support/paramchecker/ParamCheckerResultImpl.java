package com.daily.dailytest.core.utils.support.paramchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class ParamCheckerResultImpl implements ParamCheckerResult {

    //错误码
    private String code;

    //错误参数
    private List<String> errParams = new ArrayList<>();

    /**
     * 设置错误code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 添加错误参数
     * @param paramName
     */
    public void addErrParams(String paramName) {
        errParams.add(paramName);
    }

    public void setErrParams(String[] paramName) {
        errParams.addAll(Arrays.asList(paramName));
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String[] errParams() {
        return errParams.toArray(new String[errParams.size()]);
    }
}
