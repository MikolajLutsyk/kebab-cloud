package mluts.kebabcloud.domain;

import lombok.Data;

import java.util.List;

@Data
public class Kebab {
    private String name;
    private List<Ingredient> ingredients;
}
