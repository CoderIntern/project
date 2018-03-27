package rabbitmqconsumer.rabbitmqtest.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
@Component
public class WorkReceiver {
    private String receiverInstance;

    public WorkReceiver(String receiverInstance) {
        this.receiverInstance = receiverInstance;
    }

    @RabbitListener(queues = "work-queue")
    public void receive(String str) {
        System.out.println(receiverInstance.concat(" =====: ").concat(str));
    }

}
