package htwotest.htwo.controller;

import htwotest.htwo.bo.StaffBo;
import htwotest.htwo.service.IStaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/staff", produces = "application/json;charset=utf-8")
public class StaffController {
    private static final Logger logger= LoggerFactory.getLogger(StaffController.class);
    @Autowired
    private IStaffService staffService;
    @RequestMapping("/getList")
    @ResponseBody
    public List<StaffBo> getAllList(){
        List<StaffBo> staffList=null;
        try {
            staffList = staffService.queryAllStaffList();
        } catch (Exception e) {
            logger.error("查询失败");
        }
        return staffList;
    }

    @RequestMapping("/insertOne")
    @ResponseBody
    public boolean insertOne(String id, String name, int age) {
        boolean flag = false;

        return flag;
    }
}
