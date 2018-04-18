package com.daily.dailytest.core.utils.support.paramchecker;

import com.daily.dailytest.core.utils.StringTools;
import com.daily.dailytest.core.utils.support.response.bean.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 验证工具
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
public class ParamChecker {

    //日期格式校验
    private static final String PARAM_NOT_DATE_MONTH = "param [%s] must be yyyyMM";
    private static final String PARAM_NOT_DATE_DAY = "param [%s] must be yyyyMMdd";

    //byte to MB, 1048576 相当于 1 MB
    private static final long STEP = 1048576;

    private boolean isValid = true;
    private static ParamCheckerResultImpl result;

    private ParamChecker() {

    }

    public static ParamChecker newInstance() {
        synchronized (ParamChecker.class) {
            result = new ParamCheckerResultImpl();
        }
        return new ParamChecker();
    }

    /**
     * 是否验证通过
     * @return
     */
    public boolean isValid() {
        return this.isValid;
    }

    /**
     * 是否非验证通过
     * @return
     */
    public boolean isNotValid() {
        return !isValid;
    }

    /**
     * 强制设置验证结果
     * @param valid
     */
    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    /**
     * 请求参数非空检查
     * @param paramName
     * @param paramVal
     * @return
     */
    public ParamChecker notBlankCheck(String paramName, String paramVal) {
        if (!isValid) {
            return this;
        }
        if (StringUtils.isBlank(paramVal)) {
            result.setCode(ResponseCode.ERROR_MISS_PARAM);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 文件上传校验
     * @param paramName
     * @param file
     * @param suffixes
     * @param size
     * @param exclusion
     * @return
     */
    public ParamChecker checkUploadFile(String paramName, MultipartFile file, String[] suffixes, Double size, Boolean exclusion) {
        if (!isValid) {
            return this;
        }
        if (file.isEmpty()) {
            result.setCode(ResponseCode.ERROR_MISS_PARAM);
            result.addErrParams(paramName);
            this.setValid(false);
            return this;
        }
        //文件系统中的原始文件名
        String fileName = file.getOriginalFilename();
        //取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        if (null != size && size > 0 && file.getSize() > (size * STEP)) {
            result.setCode(ResponseCode.ERROR_FILE_MAX_SIZE);
            result.addErrParams(paramName);
            this.setValid(false);
            return this;
        }
        if (null != suffixes && null != exclusion) {
            for (String suffix : suffixes) {
                if (exclusion) {
                    //排除时
                    if (suffix.toLowerCase().equals(suffixName)) {
                        result.setCode(ResponseCode.ERROR_FILE_SUFFIX_NAME);
                        result.addErrParams(paramName);
                        this.setValid(false);
                        return this;
                    }
                } else {
                    //包含时
                    result.setCode(ResponseCode.ERROR_FILE_SUFFIX_NAME);
                    result.addErrParams(paramName);
                    this.setValid(false);
                    if (!exclusion && (suffix.toLowerCase().equals(suffixName))) {
                        result.setCode(ResponseCode.SUCCESS);
                        result.setErrParams(new String[]{});
                        this.setValid(true);
                        return this;
                    }
                }
            }
        }
        return this;
    }

    /**
     * 数字类型检查
     * @param paramName
     * @param paramVal
     * @return
     */
    public ParamChecker notNumberCheck(String paramName, String paramVal) {
        if (!isValid) {
            return this;
        }
        if (!NumberUtils.isNumber(paramVal)) {
            result.setCode(ResponseCode.ERROR_MISS_PARAM);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 非空时数字类型检查
     * @param paramName
     * @param paramVal
     * @return
     */
    public ParamChecker notNullNumberCheck(String paramName, String paramVal) {
        if (!isValid) {
            return this;
        }
        if (StringUtils.isNotEmpty(paramVal) && !NumberUtils.isNumber(paramVal)) {
            result.setCode(ResponseCode.ERROR_NOT_NUMBER);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 校验IP地址是否合法
     * @param paramName
     * @param paramVal
     * @return
     */
    public ParamChecker notIpCheck(String paramName, String paramVal) {
        if (!isValid) {
            return this;
        }
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        if (StringUtils.isNotEmpty(paramVal) && !paramVal.matches(rexp)) {
            result.setCode(ResponseCode.ERROR_NOT_IP);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 验证日期
     * @param paramName
     * @param paramVal
     * @param queryType
     * @return
     */
    public ParamChecker notDateCheck(String paramName, String paramVal, String queryType) {
        if (!isValid) {
            return this;
        }
        String eL = "";
        String msg = "";
        if (StringUtils.isEmpty(paramVal)) {
            result.setCode(ResponseCode.ERROR_MISS_PARAM);
            result.addErrParams(paramName);
            this.setValid(false);
            return this;
        }
        if ("day".equals(queryType)) {
            eL = "[0-9]{4}[0-9]{2}[0-9]{2}";
            msg = PARAM_NOT_DATE_DAY;
        } else if ("month".equals(queryType)) {
            eL = "[0-9]{4}[0-9]{2}";
            msg = PARAM_NOT_DATE_MONTH;
        }
        Pattern pattern = Pattern.compile(eL);
        Matcher matcher = pattern.matcher(paramVal);
        boolean dateFlag = matcher.matches();
        if (StringUtils.isNotEmpty(paramVal) && !dateFlag) {
            result.setCode(ResponseCode.ERROR_DATE_FORMAT);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 验证日期格式
     * @param paramName
     * @param paramVal
     * @return
     */
    public ParamChecker notQueryTypeCheck(String paramName, String paramVal) {
        if (!isValid) {
            return this;
        }

        boolean dataFlag = "day".equals(paramVal) || "month".equals(paramVal);
        if (StringUtils.isNotEmpty(paramVal) && !dataFlag) {
            result.setCode(ResponseCode.ERROR_DATE_FORMAT);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 参数长度校验
     * @param paramName
     * @param paramVal
     * @param length
     * @return
     */
    public ParamChecker paramLengthCheck(String paramName, String paramVal, int length) {
        if (!isValid) {
            return this;
        }
        if (StringUtils.isNotEmpty(paramVal) && length < paramVal.length()) {
            result.setCode(ResponseCode.ERROR_LENGTH_MAX);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    /**
     * 日期字符串
     * @param paramName
     * @param paramVal
     * @param formatStr
     * @return
     */
    public ParamChecker paramDateCheck(String paramName, String paramVal, String formatStr) {
        if (!isValid || StringTools.isEmptyOrNone(paramVal)) {
            return this;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            format.parse(paramVal);
        } catch (ParseException e) {
            result.setCode(ResponseCode.ERROR_DATE_FORMAT);
            result.addErrParams(paramName);
            this.setValid(false);
        }
        return this;
    }

    public ParamCheckerResult getCheckResult() {
        return result;
    }
}
