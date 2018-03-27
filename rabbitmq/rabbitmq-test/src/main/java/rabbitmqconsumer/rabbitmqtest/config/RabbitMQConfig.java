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

    @Bean
    public WorkReceiver workReceiver1() {
        return new WorkReceiver("Receiver1");
    }

    @Bean
    public WorkReceiver workReceiver2() {
        return new WorkReceiver("Receiver2");
    }

    @Bean
    public WorkReceiver workReceiver3() {
        return new WorkReceiver("Receiver3");
    }

    @Bean
    public WorkReceiver workReceiver4() {
        return new WorkReceiver("Receiver4");
    }

    @Bean
    public WorkReceiver workReceiver5() {
        return new WorkReceiver("Receiver5");
    }

    @Bean
    public WorkReceiver workReceiver6() {
        return new WorkReceiver("Receiver6");
    }

    @Bean
    public WorkReceiver workReceiver7() {
        return new WorkReceiver("Receiver7");
    }

    @Bean
    public WorkReceiver workReceiver8() {
        return new WorkReceiver("Receiver8");
    }

    @Bean
    public WorkReceiver workReceiver9() {
        return new WorkReceiver("Receiver9");
    }

    @Bean
    public WorkReceiver1 workReceiver15() {
        return new WorkReceiver1("WorkReceiver1-Receiver5");
    }

    @Bean
    public WorkReceiver1 workReceiver16() {
        return new WorkReceiver1("WorkReceiver1-Receiver6");
    }

    @Bean
    public WorkReceiver1 workReceiver17() {
        return new WorkReceiver1("WorkReceiver1-Receiver7");
    }

    @Bean
    public WorkReceiver1 workReceiver18() {
        return new WorkReceiver1("WorkReceiver1-Receiver8");
    }

    @Bean
    public WorkReceiver1 workReceiver19() {
        return new WorkReceiver1("WorkReceiver1-Receiver9");
    }

    @Bean
    public WorkReceiver1 workReceiver110() {
        return new WorkReceiver1("WorkReceiver1-Receiver110");
    }

    @Bean
    public WorkReceiver1 workReceiver111() {
        return new WorkReceiver1("WorkReceiver1-Receiver111");
    }

    @Bean
    public WorkReceiver1 workReceiver112() {
        return new WorkReceiver1("WorkReceiver1-Receiver112");
    }

    @Bean
    public WorkReceiver1 workReceiver113() {
        return new WorkReceiver1("WorkReceiver1-Receiver113");
    }

    @Bean
    public WorkReceiver1 workReceiver114() {
        return new WorkReceiver1("WorkReceiver1-Receiver114");
    }

    @Bean
    public WorkReceiver1 workReceiver115() {
        return new WorkReceiver1("WorkReceiver1-Receiver115");
    }

    @Bean
    public WorkReceiver1 workReceiver116() {
        return new WorkReceiver1("WorkReceiver1-Receiver116");
    }

    @Bean
    public WorkReceiver1 workReceiver117() {
        return new WorkReceiver1("WorkReceiver1-Receiver117");
    }

    @Bean
    public WorkReceiver1 workReceiver118() {
        return new WorkReceiver1("WorkReceiver1-Receiver118");
    }

    @Bean
    public WorkReceiver1 workReceiver119() {
        return new WorkReceiver1("WorkReceiver1-Receiver119");
    }

}
