package rabbitmqdemo.rabbbittest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig1 {

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

    /**
     * 声明一个 DirectExchange, 也可以声明多个 DirectExchange
     * @return
     */
    @Bean
    DirectExchange directExchangeA() {
        return new DirectExchange("directExchangeA");
    }

    /**
     * 声明一个队列
     * @return
     */
    @Bean
    public Queue queue1() {
        return new Queue("fanout.A");
    }

    /**
     * 声明一个队列
     * @return
     */
    @Bean
    public Queue queue2() {
        return new Queue("fanout.B");
    }

    /**
     * 绑定路由
     * @param directExchange
     * @param queue1
     * @return
     */
    @Bean
    Binding binding1(DirectExchange directExchange, Queue queue1) {
        return BindingBuilder.bind(queue1).to(directExchange).with("fanout.A");
    }

    /**
     * 绑定路由
     * @param directExchange
     * @param queue2
     * @return
     */
    @Bean
    Binding binding2(DirectExchange directExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(directExchange).with("fanout.B");
    }
}
