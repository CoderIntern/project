package com.daily.dailytest.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
@Component
public class CSDN implements CommandLineRunner {

    public static List<String> list = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        list.add("https://blog.csdn.net/dejunyang/article/details/79921612");
        list.add("https://blog.csdn.net/dejunyang/article/details/79905748");
        list.add("https://blog.csdn.net/dejunyang/arti.cle/details/79889264");
        list.add("https://blog.csdn.net/dejunyang/article/details/79874491");
        list.add("https://blog.csdn.net/dejunyang/article/details/79860250");
        list.add("https://blog.csdn.net/dejunyang/article/details/79845383");
        list.add("https://blog.csdn.net/dejunyang/article/details/79844704");
        list.add("https://blog.csdn.net/dejunyang/article/details/79837760");
        list.add("https://blog.csdn.net/dejunyang/article/details/79836775");
        list.add("https://blog.csdn.net/dejunyang/article/details/79836502");
        list.add("https://blog.csdn.net/dejunyang/article/details/79835679");
        list.add("https://blog.csdn.net/dejunyang/article/details/79829866");
        list.add("https://blog.csdn.net/dejunyang/article/details/79829740");
        list.add("https://blog.csdn.net/dejunyang/article/details/79828969");
        list.add("https://blog.csdn.net/dejunyang/article/details/79828686");
        list.add("https://blog.csdn.net/dejunyang/article/details/79826172");
        list.add("https://blog.csdn.net/dejunyang/article/details/74076515");
    }
}
