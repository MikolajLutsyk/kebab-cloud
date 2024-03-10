package mluts.kebabcloud;

import mluts.kebabcloud.domain.Ingredient;
import mluts.kebabcloud.jdbcRepositoryInterfaces.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KebabCloudApplication {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo){
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIE));
            repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIE));
            repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KebabCloudApplication.class, args);
    }
}

//todo
//add home controller as viewcontroller to webconfig
//implement spring data jdbc and post it on github

//websites to apply for jobs`
//nofluffjobs
//bulldogjobs
//justjoin.it

