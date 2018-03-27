/*
package rabbitmqdemo.rabbbittest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

*/
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
 *//*

@Component
public class WorkSend {

    @Autowired
    private Queue workQueue;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String msg = "{\"imei\":\"867717036767623\",\"csq\":\"16\",\"cellId\":\"8E52\",\"stationId\":\"1043\",\"gps\":\"48,48\",\"warehouse\":\"BLE4.0\",\"deviceId\":8,\"device\":\"70D544D4D5E5\",\"status\":\"41\",\"type\":0}" + i;
            System.out.println("send =====:".concat(msg));
            rabbitTemplate.convertAndSend(workQueue.getName(), msg);
        }
    }

}
*/
