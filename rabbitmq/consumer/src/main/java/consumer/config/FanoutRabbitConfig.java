package consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    /**
     * 配置rabbitmqmq连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory () {
        return cachingConnectionFactory();
    }

    /**
     * 配置rabbitmq缓冲连接工厂
     * @return
     */
    public CachingConnectionFactory cachingConnectionFactory () {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        cachingConnectionFactory.setPublisherConfirms(false);
        cachingConnectionFactory.setPublisherReturns(false);
        return cachingConnectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory () {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConcurrentConsumers(10);
        listenerContainerFactory.setPrefetchCount(100);
        listenerContainerFactory.setConnectionFactory(connectionFactory());
        return listenerContainerFactory;
    }

    @Bean
    public Queue workQueue() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue workQueue1() {
        return new Queue("fanout.B");
    }

}
