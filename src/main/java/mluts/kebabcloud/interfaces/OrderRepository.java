package mluts.kebabcloud.interfaces;

import mluts.kebabcloud.domain.KebabOrder;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<KebabOrder, Long> {
}
