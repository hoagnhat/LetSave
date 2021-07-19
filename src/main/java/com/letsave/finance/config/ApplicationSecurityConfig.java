package com.letsave.finance.config;
/*
    @Created: 20 / 06 / 2021 - 8:55 PM
    @Author: Dummy
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/accounts/register", "/", "/login")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .logout().invalidateHttpSession(true).deleteCookies().clearAuthentication(true)
            .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(applicationUserService).passwordEncoder(passwordEncoder);
  }


}
