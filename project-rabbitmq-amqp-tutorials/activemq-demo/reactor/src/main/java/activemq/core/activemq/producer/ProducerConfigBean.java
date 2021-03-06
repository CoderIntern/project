package activemq.core.activemq.producer;

import org.springframework.jms.core.JmsTemplate;

/**
 * <p> Date             :2018/3/31 </p>
 * <p> Module           : </p>
 * <p> Description      : 生产者配置</p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class ProducerConfigBean {

    /**
     * 队列名称
     */
    private String destinationQueueName;

    /**
     * 一次性处理条数
     */
    private int batchSize = 3000;

    /**
     * 处理间隔时长
     */
    private int intervalTimeInMillisOfCommit = 10000;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * JMS模板
     */
    private JmsTemplate jmsTemplate;

    public ProducerConfigBean(String destinationQueueName, String threadName, JmsTemplate jmsTemplate) {
        this.destinationQueueName = destinationQueueName;
        this.threadName = threadName;
        this.jmsTemplate = jmsTemplate;
    }

    public String getDestinationQueueName() {
        return destinationQueueName;
    }

    public void setDestinationQueueName(String destinationQueueName) {
        this.destinationQueueName = destinationQueueName;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getIntervalTimeInMillisOfCommit() {
        return intervalTimeInMillisOfCommit;
    }

    public void setIntervalTimeInMillisOfCommit(int intervalTimeInMillisOfCommit) {
        this.intervalTimeInMillisOfCommit = intervalTimeInMillisOfCommit;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


}
