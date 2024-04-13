package mluts.kebabcloud.security;

import mluts.kebabcloud.domain.Users;
import mluts.kebabcloud.interfaces.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
          Users user = userRepository.findByUsername(username);
          if(user != null){
              return user;
          }

          throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeRequests()
                .requestMatchers("/design", "/orders").access("hasRole('USER')")
                .requestMatchers("/", "/**").access("permitAll()")
                .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                .requestMatchers(HttpMethod.DELETE, "/api//ingredients").hasAuthority("SCOPE_deleteIngredients")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .oauth2Login().loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                //.csrf().disable()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }


}
