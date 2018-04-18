package activemq.core.activemq.jms;

import activemq.core.activemq.adapter.JMSConnectorEvent;
import activemq.core.activemq.adapter.JMSConnectorListener;
import activemq.core.activemq.adapter.JMSConnectorListenerAdapter;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * active mq jsm connector 统一类
 * <p> Date             :2017/9/20 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author kangzhonggang
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class JMSConnector implements InitializingBean, DisposableBean {
    private final static Logger LOG = LoggerFactory.getLogger(JMSConnector.class);

    private int batchSize = 1000;
    private long intervalTimeInMillisOfCommit = 5000L;
    private int bufferSize = -1;
    private String threadName = "JMSConnector";

    private JmsTemplate jmsTemplate;
    private String destinationQueueName;
    private ActiveMQQueue destinationQueue;
    private LinkedBlockingQueue innerQueue;
    private Thread workerThread;
    private JMSConnectorListener jmsConnectorListener = new JMSConnectorListenerAdapter();

    /**
     * Status
     */
    private final static int CREATED = 0;
    private final static int RUNNING = 1;
    private final static int STOPPING = 2;
    private final static int STOPPED = 3;
    private final AtomicInteger status = new AtomicInteger(CREATED);
    private CountDownLatch latch = new CountDownLatch(1);


    /**
     * 发送String类型的消息
     *
     * @param message
     */
    public void send(final Serializable message) {
        jmsConnectorListener.beforeSend(new JMSConnectorEvent(threadName, JMSConnectorEvent.Type.SEND, message));
        doSend(message);
        jmsConnectorListener.afterSend(new JMSConnectorEvent(threadName, JMSConnectorEvent.Type.SEND, message));
    }

    public void doSend(final Serializable message) {
        ensureWork();
        try {
            innerQueue.put(message);
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            status.set(STOPPING);
        }
    }


    /**
     * 延迟发送消息，支持activemq的延迟发送特性
     *
     * @param message
     * @param delaySendTimeInMillis 延迟发送时间,单位ms
     */
    public void delaySend(final Serializable message, final int delaySendTimeInMillis) {
        jmsConnectorListener.beforeSend(new JMSConnectorEvent(threadName, JMSConnectorEvent.Type.DELAY_SEND, message));
        doDelaySend(message, delaySendTimeInMillis);
        jmsConnectorListener.afterSend(new JMSConnectorEvent(threadName, JMSConnectorEvent.Type.DELAY_SEND, message));
    }

    public void doDelaySend(final Serializable message, final int delaySendTimeInMillis) {
        ensureWork();
        try {
            innerQueue.put(new DelayMessageWrapper(message, delaySendTimeInMillis));
        } catch (InterruptedException e) {
            LOG.info(e.getMessage());
            status.set(STOPPING);
        }
    }

    /**
     * to ensure that JMSConnector start to serve until worker thread is ready.
     */
    private void ensureWork() {
        if (status.get() > RUNNING) {
            throw new IllegalStateException("JMSConnector is stopping");
        }
        if (status.get() == RUNNING) {
            return;
        } else if (status.get() == CREATED) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jmsTemplate == null) {
            throw new IllegalArgumentException("jmsTemplate must not be null");
        }

        if (StringUtils.isBlank(destinationQueueName)) {
            throw new IllegalArgumentException("destinationQueueName must not be null or ");
        }

        if (!jmsTemplate.isSessionTransacted()) {
            throw new IllegalArgumentException("jmsTemplate must be sessionTransacted when transactional enable");
        }

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

        workerThread = new Thread(null, new Runnable() {
            @Override
            public void run() {
                while (status.get() == RUNNING || (status.get() == STOPPING && innerQueue.size() > 0)) {
                    long startTime = System.currentTimeMillis();
                    long currentTime = System.currentTimeMillis();
                    int batchCount = 0;
                    final List<Serializable> buffer = new ArrayList<>();
                    while (batchCount < batchSize && (currentTime - startTime) < intervalTimeInMillisOfCommit) {
                        Serializable msg;
                        try {
                            msg = (Serializable) innerQueue.poll(1, TimeUnit.SECONDS);
                        } catch (InterruptedException e) {
                            LOG.info("JMS Send Thread Handler is interrupted");
                            status.compareAndSet(RUNNING, STOPPING);
                            break;
                        }
                        if (msg == null) {
                            currentTime = System.currentTimeMillis();
                            continue;
                        }
                        buffer.add(msg);
                        batchCount++;
                        currentTime = System.currentTimeMillis();
                    }
                    if (buffer.size() == 0) {
                        continue;
                    }
                    try {
                        sendInternal(buffer);
                    } catch (JMSException e) {
                        try {
                            sendInternal(buffer);
                        } catch (JMSException e1) {
                        }
                    }
                }
            }
        }, threadName);
        workerThread.start();
        status.compareAndSet(CREATED, RUNNING);
        latch.countDown();
        this.jmsConnectorListener.setJMSConnector(this);
        LOG.info("{} is started", threadName);
    }

    private void sendInternal(final List<Serializable> buffer) throws JMSException {
        try {
            jmsTemplate.execute(destinationQueue, new ProducerCallback<Void>() {
                @Override
                public Void doInJms(Session session, MessageProducer producer) throws JMSException {
                    for (Serializable item : buffer) {
                        if (item instanceof DelayMessageWrapper) {
                            DelayMessageWrapper delayMessageWrapper = (DelayMessageWrapper) item;
                            Serializable messageBody = delayMessageWrapper.messageBody;
                            Message message;
                            if (messageBody instanceof String) {
                                message = session.createTextMessage((String) messageBody);
                            } else {
                                message = session.createObjectMessage(messageBody);
                            }
                            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayMessageWrapper.delaySendTimeInMillis);
                            producer.send(message);
                        } else {
                            Message message;
                            if (item instanceof String) {
                                message = session.createTextMessage(((String) item).intern());
                            } else {
                                message = session.createObjectMessage(item);
                            }
                            producer.send(message);
                        }
                    }

                    if (buffer.size() > 0) {
                        try {
                            session.commit();
                            LOG.info("buffer committed : {} , approximate left : {}", buffer.size(), innerQueue.size());
                            jmsConnectorListener.afterCommit(new JMSConnectorEvent(threadName, JMSConnectorEvent.Type.COMMIT, buffer.size()));
                        } catch (JMSException e) {
                            LOG.error("commit error");
                            throw e;
                        }
                    }
                    return null;
                }
            });
        } catch (JmsException e) {
            LOG.error("Executing Error", e);
            LOG.error("Uncommitted message in buffer , size : {} , approximate left : {} , retry later", buffer.size(), innerQueue.size());
            throw e;
        }
    }

    @Override
    public void destroy() throws Exception {
        status.compareAndSet(RUNNING, STOPPING);
        workerThread.join();
        status.compareAndSet(STOPPING, STOPPED);
        jmsConnectorListener.setJMSConnector(null);
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setIntervalTimeInMillisOfCommit(long intervalTimeInMillisOfCommit) {
        this.intervalTimeInMillisOfCommit = intervalTimeInMillisOfCommit;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setDestinationQueueName(String destinationQueueName) {
        this.destinationQueueName = destinationQueueName;
        this.destinationQueue = new ActiveMQQueue(destinationQueueName);
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setJmsConnectorListener(JMSConnectorListener jmsConnectorListener) {
        this.jmsConnectorListener = jmsConnectorListener;
    }

    private static class DelayMessageWrapper implements Serializable {
        final Serializable messageBody;
        final int delaySendTimeInMillis;

        public DelayMessageWrapper(Serializable messageBody, int delaySendTimeInMillis) {
            this.messageBody = messageBody;
            this.delaySendTimeInMillis = delaySendTimeInMillis;
        }
    }

}
