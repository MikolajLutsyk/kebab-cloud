package mluts.kebabcloud;

import mluts.kebabcloud.domain.Ingredient;
import mluts.kebabcloud.domain.Kebab;
import mluts.kebabcloud.domain.Users;
import mluts.kebabcloud.interfaces.IngredientRepository;
import mluts.kebabcloud.interfaces.KebabRepository;
import mluts.kebabcloud.interfaces.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class KebabCloudApplication implements WebMvcConfigurer {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository, UserRepository userRepository, KebabRepository kebabRepository, PasswordEncoder encoder){
        return args -> {
            Ingredient flto = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient coto = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient grbf = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carn = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tmto = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIE);
            Ingredient chkn = new Ingredient("chkn", "Chicken", Ingredient.Type.PROTEIN);
            Ingredient letc = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIE);
            Ingredient ched = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient slsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient srcr = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

            Kebab kebab1 = new Kebab();
            kebab1.setName("Babasya Kebab");
            kebab1.setIngredients(Arrays.asList(flto, tmto, chkn, ched, srcr, letc));

            ingredientRepository.save(flto);
            ingredientRepository.save(coto);
            ingredientRepository.save(grbf);
            ingredientRepository.save(carn);
            ingredientRepository.save(tmto);
            ingredientRepository.save(chkn);
            ingredientRepository.save(letc);
            ingredientRepository.save(ched);
            ingredientRepository.save(jack);
            ingredientRepository.save(slsa);
            ingredientRepository.save(srcr);
            kebabRepository.save(kebab1);
            userRepository.save(new Users("mluts", encoder.encode("password"), "Mykola Lutsyk", "Nadbystrzycka", "Lublin", "Poland", "12345", "123123123"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KebabCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}

//todo
//configure ssl for the application
//configure profiles for configuration when there is need to enter production phase

//websites to apply for jobs`
//nofluffjobs
//bulldogjobs
//justjoin.it

