package rabbitmqdemo.rabbbittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmqdemo.rabbbittest.beans.Person;
import rabbitmqdemo.rabbbittest.config.FanoutSender;
import rabbitmqdemo.rabbbittest.config.FanoutSender1;
import rabbitmqdemo.rabbbittest.utils.ProtoStuffSerializerUtil;

@RestController
@RequestMapping(value = "/rabbitmq")
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private FanoutSender1 fanoutSender1;

    @RequestMapping(value = "/send")
    public String send(@RequestBody Person person) {
        byte[] serializePerson = ProtoStuffSerializerUtil.serialize(person);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            fanoutSender1.send(serializePerson);
            fanoutSender1.send1(serializePerson);
        }
        return "send ok";
    }
}