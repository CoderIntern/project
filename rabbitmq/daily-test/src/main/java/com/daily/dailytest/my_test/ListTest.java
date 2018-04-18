package com.daily.dailytest.my_test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p> Date             :2018/3/21 </p>
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
public class ListTest {

    private void test1() {
        ReportBO reportBO = new ReportBO();
        setReportBO(reportBO);
        System.out.println(reportBO);
    }

    private void test2() {
        Object obj = null;
        ReportBO reportBO = (ReportBO) obj;
    }

    private void setReportBO(ReportBO bo) {
        bo.setGwId("aksdlfjaksdj");
    }

    public static void main(String[] args) {
        ListTest l = new ListTest();
        l.test2();
    }

}
