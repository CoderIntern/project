package rabbitmqconsumer.rabbitmqtest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Date             :2018/3/20 </p>
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
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue workQueue() {
        return new Queue("work-queue");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPrefetchCount(0);
        return factory;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =  new CachingConnectionFactory("47.93.172.8", 5672);
        cachingConnectionFactory.setUsername("rabbitmq");
        cachingConnectionFactory.setPassword("12345qwert");
        return cachingConnectionFactory;
    }

    @Bean
    public WorkReceiver workReceiver() {
        return new WorkReceiver("Receiver0");
    }

}
