package com.myself.dubbodemoprovider;

import com.alibaba.dubbo.rpc.RpcContext;
import com.myself.dubbodemoapi.DemoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> Date             :2018/2/24 </p>
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
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {

        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();

    }
}
