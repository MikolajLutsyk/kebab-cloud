package mluts.kebabcloud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient{

    @Id
    @Column(name = "id")
    private final String id;
    private final String name;
    private final Type type;


    public enum Type{
        WRAP, PROTEIN, VEGGIE, CHEESE, SAUCE
    }
}
