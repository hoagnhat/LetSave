package com.letsave.finance.config;
/*
    @Created: 20 / 06 / 2021 - 9:17 PM
    @Author: Dummy
*/

import com.letsave.finance.exception.exceptions.EntityNotFoundException;
import com.letsave.finance.mapper.AccountMapper;
import com.letsave.finance.model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserService implements UserDetailsService {

  @Autowired
  AccountMapper mapper;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    AccountModel account = mapper.findAccountByColumn("username", username);

    if (account == null) {
      throw new UsernameNotFoundException("Username isn't existed!");
    }

    List<GrantedAuthority> list = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
    list.add(authority);

    ApplicationUser user = ApplicationUser.builder()
                                          .username(account.getUsername())
                                          .password(account.getPassword())
                                          .grantedAuthorities(list)
                                          .isAccountNonExpired(true)
                                          .isAccountNonLocked(true)
                                          .isCredentialsNonExpired(true)
                                          .isEnabled(true)
                                          .build();

    return user;

  }

}
