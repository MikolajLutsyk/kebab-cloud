package mluts.kebabcloud.jdbcRepositoryInterfaces;

import mluts.kebabcloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
