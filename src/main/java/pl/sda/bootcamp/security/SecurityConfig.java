package pl.sda.bootcamp.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler successHandler;

    public SecurityConfig(@Qualifier("appUserDetailsService")
                          final UserDetailsService userDetailsService,
                          AuthenticationSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            //.hasRole dostawia do stringa z parametru przedrostek ROLE_ więc tak
            // trzeba przechowywać nazwy ról w bazie, np ROLE_ADMIN.
            // MOŻNA alternatywnie użyć metody .hasAuthority i tu trzeba podać nazwę
            // roli już dokładnie TAK JAK jest zapisana w BD:
            //.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
            .antMatchers("/panel-klienta/**").hasRole("USER")
            .antMatchers("/panel-trenera/**").hasRole("TEACHER")
            .anyRequest().permitAll()
            .and().formLogin().loginPage("/login").successHandler(successHandler)
            .and().logout().logoutSuccessUrl("/")
            .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    //aaa: qqq , ttt: www, sss: www
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("aaa")
            .password("$2y$12$f0EN/pLt5wRguPnpkw7MxOl30z34VYGMYoRX6dUKMtRw.ct8fZN3m")
            .roles("ADMIN")
            .and()
            .withUser("ttt")
            .password("$2y$12$f0EN/pLt5wRguPnpkw7MxOl30z34VYGMYoRX6dUKMtRw.ct8fZN3m")
            .roles("TEACHER")
            .and()
            .withUser("sss")
            .password("$2y$12$f0EN/pLt5wRguPnpkw7MxOl30z34VYGMYoRX6dUKMtRw.ct8fZN3m")
            .roles("USER");
    }*/
}
