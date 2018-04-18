package com.daily.dailytest.core.utils.support.ftp;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Date             :2018/1/19 </p>
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
public abstract class AbstractFtpClientPool<T> {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(AbstractFtpClientPool.class);

    //对象连接池
    private GenericObjectPool<T> innerPool;

    //如果一个对象borrow之后10分钟还没有返还给pool，认为是泄漏的对象
    private static final int ABANDONED_TIMOUT = 600;

    //运行回收对象时间 10 ms
    private static final int RUN_EVICTION_TIME = 10000;

    //初始化连接池
    public AbstractFtpClientPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        initPool(poolConfig, factory);
    }

    /**
     * 初始化连接池
     * @param poolConfig
     * @param factory
     */
    private void initPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        if (this.innerPool != null) {
            logger.info("innerPool is not null, destroy it ...");
            innerPool.close();
        }
        this.innerPool = new GenericObjectPool<>(factory, poolConfig);
        //防止内存溢出
        AbandonedConfig abandonedConfig = new AbandonedConfig();
        //在Maintenance的时候检查是否有泄漏
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        //borrow的时候检查泄漏
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setRemoveAbandonedTimeout(ABANDONED_TIMOUT);
        this.innerPool.setAbandonedConfig(abandonedConfig);
        //运行一次维护任务
        this.innerPool.setTimeBetweenEvictionRunsMillis(RUN_EVICTION_TIME);
    }

    /**
     * 获取一个资源
     * @return
     */
    public T getResource() {
        //获取资源
        T result = null;
        try {
            //获取资源
            result = innerPool.borrowObject();
            //未获取到对象，或验证失败，将重新创建对象
            if (null == result || !innerPool.getFactory().validateObject(new DefaultPooledObject<>(result))) {
                //使对象在池中失效
                innerPool.invalidateObject(result);
                //重新创建一个对象，如加到对象池中
                innerPool.addObject();
                //再次从对象池中获取对象
                result = innerPool.borrowObject();
            }
        } catch (Exception e) {
            logger.warn("pool get resourcecenter error!!!", e);
        }
        return result;
    }

    public void returnResource(T resource) {
        if (innerPool.getFactory().validateObject(new DefaultPooledObject<>(resource))) {
            innerPool.returnObject(resource);
            return;
        }
        try {
            innerPool.getFactory().destroyObject(new DefaultPooledObject<>(resource));
            innerPool.invalidateObject(resource);
        } catch (Exception e) {
            logger.warn("pool invalidate resourcecenter error!!!");
        }
        //空闲数大于等于激活数时，清理掉空闲连接
        if (getNumIdle() >= getNumActive()) {
            innerPool.clear();
        }
    }

    /**
     * 获取激活数
     * @return
     */
    private int getNumActive() {
        return innerPool.getNumActive();
    }

    /**
     * 获取空闲数
     * @return
     */
    private int getNumIdle() {
        return innerPool.getNumIdle();
    }

    public void destroy() {
        //TODO YANGDEJUN 待解决未回归对象，释放问题
        innerPool.clear();
        innerPool.close();
    }


}
