package mluts.kebabcloud.interfaces;

import mluts.kebabcloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
