package mluts.kebabcloud.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class KebabOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Date placedAt = new Date();

    @NotBlank(message = "delivery name is required")
    private String deliveryName;

    @NotBlank(message = "delivery street is required")
    private String deliveryStreet;

    @NotBlank(message = "delivery city is required")
    private String deliveryCity;

    @NotBlank(message = "delivery country is required")
    private String deliveryCountry;

    @NotBlank(message = "postal code is required")
    private String deliveryPostalCode;

    @CreditCardNumber(message = "not a valid card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Kebab> kebabs = new ArrayList<>();

    public void addKebab(Kebab kebab){
        this.kebabs.add(kebab);
    }
}
