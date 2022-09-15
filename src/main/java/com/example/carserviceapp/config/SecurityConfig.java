package com.example.carserviceapp.config;

import com.example.carserviceapp.model.Role;
import com.example.carserviceapp.security.jwt.JwtConfigurer;
import com.example.carserviceapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_ADMIN = Role.RoleName.ADMIN.name();
    private static final String ROLE_USER = Role.RoleName.USER.name();
    private static final String ROLE_MASTER = Role.RoleName.MASTER.name();
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/login", "/swagger-ui/**", "/swagger-resources/**",
                        "/v3/api-docs", "/v2/api-docs", "/cars/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/users/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.POST,
                        "/users/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.PUT,
                        "/users/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.DELETE,
                        "/users/**").hasRole(ROLE_ADMIN)
                .anyRequest()
                .authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .headers().frameOptions().disable();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}