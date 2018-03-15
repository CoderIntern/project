package htwotest.htwo.service.impl;

import htwotest.htwo.bo.StaffBo;
import htwotest.htwo.dao.StaffRepository;
import htwotest.htwo.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class IStaffServiceImpl implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;
    /*@Override
    public List<StaffBo> queryAllStaffList() {
        Iterable<StaffBo> iterable = staffRepository.findAll();
        List<StaffBo> list=new ArrayList<StaffBo>();
        Iterator<StaffBo> iterator = iterable.iterator();
        while(iterator.hasNext()){
            StaffBo next = iterator.next();
            list.add(next);
        }
        return list;
    }*/

    @Override
    public List<StaffBo> queryAllStaffList() {
        //分页
        Pageable pageable = new PageRequest(0, 3);
        Page<StaffBo> page =  staffRepository.findAll(pageable);
        List<StaffBo> staffBoList = page.getContent();
//        return staffBoList;

        //查询全部数据
        Iterable<StaffBo> iterable = staffRepository.findAll();
        List<StaffBo> list=new ArrayList<StaffBo>();
        Iterator<StaffBo> iterator = iterable.iterator();
        while(iterator.hasNext()){
            StaffBo next = iterator.next();
            list.add(next);
        }
        return list;
    }
}
