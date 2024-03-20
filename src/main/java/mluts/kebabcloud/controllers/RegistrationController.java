package mluts.kebabcloud.controllers;


import lombok.extern.slf4j.Slf4j;
import mluts.kebabcloud.interfaces.UserRepository;
import mluts.kebabcloud.security.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistrationForm(RegistrationForm form){
        log.info("Registration submitted: {}", form);
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
