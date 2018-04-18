package com.daily.dailytest.csdn;

import com.daily.dailytest.core.base.BaseController;
import com.daily.dailytest.core.utils.HttpUtil;
import com.daily.dailytest.runner.CSDN;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Random;

/**
 * <p> Date             :2018/4/13 </p>
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
@RestController
@RequestMapping("/csnd")
public class CSDNTest extends BaseController {

    @GetMapping("/test")
    public void csdn () throws InterruptedException, UnknownHostException {
        for (int i = 0; i < 500; i ++) {
            InetAddress addr = InetAddress.getLocalHost();
            String ip=addr.getHostAddress().toString(); //获取本机ip
            System.out.println(ip);
            int random = getRandom();
            String url = CSDN.list.get(random);
            String s = HttpUtil.get(url);
            System.out.println(Calendar.getInstance().getTimeInMillis() + "->" + i + "->" + url);
            Thread.sleep(60000);
        }
        /*while (true) {
            Thread.sleep(600000);
            int random = getRandom();
            String url = CSDN.list.get(random);
            String s = HttpUtil.get(url);
            System.out.println(Calendar.getInstance().getTimeInMillis() + url);

            if (false) {
                return;
            }
        }*/

    }

    private static int getRandom() {
        return new Random().nextInt(CSDN.list.size());
    }

}
