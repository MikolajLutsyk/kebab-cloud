package mluts.kebabcloud.interfaces;

import mluts.kebabcloud.domain.Kebab;
import org.springframework.data.repository.CrudRepository;

public interface KebabRepository extends CrudRepository<Kebab, Long>{
}
