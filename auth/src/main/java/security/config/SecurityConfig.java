package security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.authentication.AuthTokenFilter;
import service.user.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImplementation userDetailsServiceImplementation;
    private final AuthenticationJwtEntry authenticationJwtEntry;

    @Autowired
    public SecurityConfig(UserDetailsServiceImplementation userDetailsServiceImplementation, AuthenticationJwtEntry authenticationJwtEntry) {
        this.userDetailsServiceImplementation = userDetailsServiceImplementation;
        this.authenticationJwtEntry = authenticationJwtEntry;
    }

    @Bean
    public AuthTokenFilter authenticationTokenFilterJwt() {
        return new AuthTokenFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(bCryptPasswordEncoder());
    }

}
