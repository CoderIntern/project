package rabbitmqdemo.rabbbittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmqdemo.rabbbittest.config.FanoutSender;

@RestController
@RequestMapping(value = "/rabbitmq")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @RequestMapping(value = "/send")
    public String send() {
        for (int i = 0; i < 100; i++) {
            fanoutSender.send();
        }

        return "send ok";
    }
}