package com.hasanlo.config.security;

import com.hasanlo.config.security.jwt.JWTAuthenticationFilter;
import com.hasanlo.config.security.jwt.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    static final Integer ENCODING_STRENGTH = 256;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Override
    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/rest/*").hasRole("USER")
                .antMatchers("*").permitAll()
                .and()
                .addFilterBefore(getJWTLoginFilter(),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name = "passwordEncoder")
    public BasePasswordEncoder passwordencoder() {
        return new ShaPasswordEncoder(ENCODING_STRENGTH);
    }

    @Bean
    public JWTLoginFilter getJWTLoginFilter() throws Exception {
        // http://host:8080/login/ -> post -> body ->  {"username": "user1","password":"123456"}
        return new JWTLoginFilter("/login", authenticationManager());
    }

}
