package mluts.kebabcloud.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class KebabOrder {
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryCountry;
    private String deliveryPostalCode;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Kebab> kebabs = new ArrayList<>();

    public void addKebab(Kebab kebab){
        this.kebabs.add(kebab);
    }
}
