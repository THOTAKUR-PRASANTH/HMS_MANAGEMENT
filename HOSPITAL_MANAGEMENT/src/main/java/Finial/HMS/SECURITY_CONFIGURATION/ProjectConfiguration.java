package Finial.HMS.SECURITY_CONFIGURATION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrfconfig -> csrfconfig.disable());

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/sucess","/Admindashboard").hasRole("ADMIN")
                .anyRequest().permitAll());


        http.formLogin(flc -> flc
                .loginPage("/login")
                .defaultSuccessUrl("/sucess")
                .failureUrl("/login?error=true"));

        http.httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
