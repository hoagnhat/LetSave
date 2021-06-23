package com.letsave.finance.service;
/*
    @Created: 20 / 06 / 2021 - 3:12 PM
    @Author: Dummy
*/

import com.letsave.finance.mapper.ProfileMapper;
import com.letsave.finance.model.AccountModel;
import com.letsave.finance.model.ProfileModel;
import com.letsave.finance.request.UpdateProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

  private final ProfileMapper mapper;

  @Autowired
  public ProfileService(ProfileMapper mapper) {
    this.mapper = mapper;
  }

  public void insertProfile(ProfileModel profile) {
    mapper.insertProfile(profile);
  }

  public void updateProfile(long accountId, UpdateProfileRequest request) {

    ProfileModel profile = ProfileModel.builder()
            .accountId(accountId)
            .fullname(request.getFullname())
            .email(request.getEmail())
            .phone(request.getPhone())
            .birthday(request.getBirthday())
            .avatar(request.getAvatar())
            .build();

    mapper.updateProfile(profile);

  }

  public ProfileModel findProfileByColumn(String column, String value) {
    return mapper.findProfileByColumn(column, value);
  }

}
