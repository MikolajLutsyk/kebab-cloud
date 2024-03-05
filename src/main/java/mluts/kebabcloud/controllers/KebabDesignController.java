package mluts.kebabcloud.controllers;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mluts.kebabcloud.domain.Ingredient;
import mluts.kebabcloud.domain.Kebab;
import mluts.kebabcloud.domain.KebabOrder;
import mluts.kebabcloud.jdbcRepositoryInterfaces.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("kebabOrder")
public class KebabDesignController {

    private final IngredientRepository ingredientRepository;


    public KebabDesignController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }


    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "kebabOrder")
    public KebabOrder order(){
        return new KebabOrder();
    }

    @ModelAttribute(name = "kebab")
    public Kebab kebab(){
        return new Kebab();
    }

    @GetMapping
    public String showDesignView(){
        return "design";
    }

    @PostMapping
    public String processKebab(@Valid Kebab kebab, Errors errors,
                               @ModelAttribute KebabOrder kebabOrder){

        if (errors.hasErrors()){
            return "design";
        }

        kebabOrder.addKebab(kebab);
        log.info("Processing kebab: {}", kebab);
        return "redirect:/orders/current";
    }

    public Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
