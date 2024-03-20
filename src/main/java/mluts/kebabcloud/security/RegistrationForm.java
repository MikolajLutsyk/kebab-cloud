package mluts.kebabcloud.security;

import lombok.Data;
import mluts.kebabcloud.domain.Users;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String country;
    private final String postalCode;
    private final String phoneNumber;

    public Users toUser(PasswordEncoder passwordEncoder){
        return new Users(username, passwordEncoder.encode(password), fullname, street, city, country, postalCode, phoneNumber);
    }
}
