package rabbitmqdemo.rabbbittest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender1 {

    protected static Logger logger= LoggerFactory.getLogger(FanoutSender1.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(byte[] bytes) {
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(bytes, messageProperties);
        rabbitTemplate.send("directExchangeA", "fanout.A", message);
    }

    public void send1(byte[] bytes) {
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(bytes, messageProperties);
        rabbitTemplate.send("directExchangeA", "fanout.B", message);
    }

}
