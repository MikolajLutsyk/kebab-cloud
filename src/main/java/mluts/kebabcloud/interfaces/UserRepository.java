package mluts.kebabcloud.interfaces;

import mluts.kebabcloud.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
