package mluts.kebabcloud.controllers;


import lombok.extern.slf4j.Slf4j;
import mluts.kebabcloud.domain.Ingredient;
import mluts.kebabcloud.domain.Kebab;
import mluts.kebabcloud.domain.KebabOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("kebabOrder")
public class KebabDesignController {


    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("BEEF", "Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("GRSC", "Garlic Sauce", Ingredient.Type.SAUCE),
                new Ingredient("TMTO", "Tomato", Ingredient.Type.VEGGIE)
                );

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
    public String processKebab(Kebab kebab,
                               @ModelAttribute KebabOrder kebabOrder){
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
