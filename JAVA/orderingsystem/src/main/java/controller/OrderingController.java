package controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import entity.Ordering;
import entity.User;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.OrderingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderingService orderingService;

    @RequestMapping(value = "/ordering")
    public void ordering(String name) {
        System.out.println(name);
        if (null != name && name.length() > 0) {
            String[] userIds = name.split(",");
            for (String id : userIds) {
                if (null != id && id.length() > 0) {
                    System.out.println(name);
                    orderingService.ordering(id);
                }
            }
        } else {

            System.out.println("request error");
        }

    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertAndReturnState(@RequestParam("name") String name) {
        System.out.println(name);
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(name);
        map.put("issuccess", false);
        if (name != null && name.length() > 0) {
            if(orderingService.queryExistByName(Integer.parseInt(name))){
                map.put("issuccess",false);
            } else{
                orderingService.ordering(name);
                map.put("issuccess",true);
            }
        }
        System.out.println(map.get("issuccess"));
        return map;
    }

    @RequestMapping(value = "/orderedLists")
    public String queryAllOrdered(Model model) {
        List<Ordering> orderings = orderingService.queryAllOrdered();
        System.out.println("---------------" + orderings);
        model.addAttribute("orderings", orderings);
        for (Ordering ordering:orderings
             ) {
            System.out.println(ordering);
        }
        return "orderedList";
    }
}
