package com.daily.dailytest.core.utils;

/**
 * <p> Date             :2018/1/18 </p>
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
public class StringTools {
    private StringTools() {

    }

    public static boolean isEmptyOrNone(String str) {
        return str == null || "".equals(str.trim());
    }
}
