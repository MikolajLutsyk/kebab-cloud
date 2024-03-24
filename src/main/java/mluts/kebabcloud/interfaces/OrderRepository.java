package mluts.kebabcloud.interfaces;

import mluts.kebabcloud.domain.KebabOrder;
import mluts.kebabcloud.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<KebabOrder, Long> {
    List<KebabOrder> findByUserOrderByPlacedAtDesc(Users users, Pageable pageable);
}
