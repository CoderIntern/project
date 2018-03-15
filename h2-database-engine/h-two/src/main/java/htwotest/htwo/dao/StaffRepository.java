package htwotest.htwo.dao;

import htwotest.htwo.bo.StaffBo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<StaffBo, String> {

}
