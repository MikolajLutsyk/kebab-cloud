package mluts.kebabcloud.domain;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class KebabOrder {

    private static final long serialVersionUID = 1L;
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


    private List<Kebab> kebabs = new ArrayList<>();

    public void addKebab(Kebab kebab){
        this.kebabs.add(kebab);
    }
}
