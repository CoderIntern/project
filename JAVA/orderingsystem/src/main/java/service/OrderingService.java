package service;

import dao.OrderingDao;
import entity.Ordering;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderingService {
    @Autowired
    private OrderingDao orderingDao;
    public boolean queryExistByName(@Param("user_id") int user_id){
        if (orderingDao.queryExistByName(user_id).size() > 0){
            return true;
        }
        return false;
    }
    public void ordering(@Param("name") String name){
        orderingDao.ordering(name);
    }
    public List<Ordering> queryAllOrdered(){
        return orderingDao.queryAllOrdered();
    }
}
