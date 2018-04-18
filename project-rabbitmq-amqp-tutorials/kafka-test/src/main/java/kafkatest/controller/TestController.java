package kafkatest.controller;

import kafkatest.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TestController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping(value = "/test")
    public void test() {
        kafkaProducer.send();
    }
}
