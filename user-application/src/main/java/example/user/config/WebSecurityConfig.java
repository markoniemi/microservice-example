package example.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import example.user.security.UserRepositoryAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
  @Autowired
  UserRepositoryAuthenticationProvider userRepositoryAuthenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
        .defaultSuccessUrl("/user/users").usernameParameter("j_username")
        .passwordParameter("j_password").failureUrl("/login?error=true").permitAll();
    http.logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login").permitAll();
    http.authorizeRequests()
        .requestMatchers("/", "/home", "/api/**", "/actuator/**", "/static/**", "/webjars/**")
        .permitAll().anyRequest().authenticated();
    return http.build();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(userRepositoryAuthenticationProvider);
  }
}
