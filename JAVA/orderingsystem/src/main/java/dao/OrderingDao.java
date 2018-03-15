package dao;

import entity.Ordering;
import entity.User;

import java.util.List;

public interface OrderingDao {
    List<Ordering> queryExistByName(int user_id);
    void ordering(String name);
    List<Ordering> queryAllOrdered();
}
