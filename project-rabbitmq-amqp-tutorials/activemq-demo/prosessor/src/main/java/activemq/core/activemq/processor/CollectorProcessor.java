package activemq.core.activemq.processor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消费者示例代码，后续迁移
 *
 * kang
 * 2017.09.22
 */
public abstract class CollectorProcessor implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(CollectorProcessor.class);

    private int batchSize = 1000;
    private long intervalTimeInMillisOfCommit = 5000L;
    private int bufferSize = 10000;
    private final AtomicBoolean workerStatus = new AtomicBoolean(false);
    private String threadName;
    private LinkedBlockingQueue innerQueue;
    private Thread workerThread;

    public void process(Object bean) {
        try {
            innerQueue.put(bean);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
            workerStatus.compareAndSet(true, false);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (batchSize < 0) {
            throw new IllegalArgumentException("batchSize must be greater than 0");
        }

        if (intervalTimeInMillisOfCommit < 0L) {
            throw new IllegalArgumentException("intervalTimeInMillisOfCommit must be greater than 0");
        }

        if (bufferSize < 0) {
            innerQueue = new LinkedBlockingQueue();
        } else {
            innerQueue = new LinkedBlockingQueue(bufferSize);
        }

        Runnable run = new Runnable() {
            @Override
            public void run() {
                while (workerStatus.get()) {
                    long startTime = System.currentTimeMillis();
                    long currentTime = System.currentTimeMillis();
                    int batchCount = 0;
                    final List buffer = new ArrayList();

                    while (batchCount < batchSize && (currentTime - startTime) < intervalTimeInMillisOfCommit) {
                        Object bean;
                        try {
                            bean = innerQueue.poll(1, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            workerStatus.compareAndSet(true, false);
                            break;
                        }
                        if (bean == null) {
                            currentTime = System.currentTimeMillis();
                            continue;
                        }
                        buffer.add(bean);
                        batchCount++;
                        currentTime = System.currentTimeMillis();
                    }

                    if (batchCount > 0) {
                        batchList(buffer);
                    }
                }
            }
        };
        workerStatus.compareAndSet(false, true);
        workerThread = new Thread(null, run, threadName);
        workerThread.start();
    }

    public void destroy() throws Exception {
        workerStatus.compareAndSet(true, false);
        workerThread.interrupt();
        workerThread.join(5000L);
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setIntervalTimeInMillisOfCommit(long intervalTimeInMillisOfCommit) {
        this.intervalTimeInMillisOfCommit = intervalTimeInMillisOfCommit;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
        if(StringUtils.isBlank(threadName)) {
            throw new RuntimeException("this processor thread name is empty.");
        }
    }

    public abstract void batchList(List<Serializable> list);
}
