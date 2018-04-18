package rabbitmqdemo.rabbbittest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    protected static Logger logger= LoggerFactory.getLogger(FanoutSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        logger.info("Sender : " + context);
        /**
         * AmqpTemplate 调用
         *      convertAndSend(String exchange, String routingKey, final Object object); 调用
         *          convertAndSend(exchange, routingKey, object, (CorrelationData) null);调用
         *              send(final String exchange, final String routingKey, final Message message, final CorrelationData correlationData) throws AmqpException 调用
         *                  execute(final ChannelCallback<T> action, final ConnectionFactory connectionFactory) 重写ChannelCallback方法，调用
         *                      execute(RetryCallback<T, E> retryCallback, RecoveryCallback<T> recoveryCallback) throws E
         */
        this.rabbitTemplate.convertAndSend("fanoutExchanges", "", context);

    }

}
