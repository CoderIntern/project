package activemq.core.base;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

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
public class BaseBean implements Serializable {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

    @Override
    public String toString() {
        try {
            return BeanUtils.describe(this).toString();
        } catch (Exception e) {
            logger.info("BaseBean ... ", e);
            return super.toString();
        }
    }
}
