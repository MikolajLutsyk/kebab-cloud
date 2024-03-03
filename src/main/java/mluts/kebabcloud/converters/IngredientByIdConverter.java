package mluts.kebabcloud.converters;

import mluts.kebabcloud.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter(){
        ingredientMap.put("FLTO",new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("COTO",new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("BEEF",new Ingredient("BEEF", "Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("CHED",new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientMap.put("GRSC",new Ingredient("GRSC", "Garlic Sauce", Ingredient.Type.SAUCE));
        ingredientMap.put("TMTO",new Ingredient("TMTO", "Tomato", Ingredient.Type.VEGGIE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
