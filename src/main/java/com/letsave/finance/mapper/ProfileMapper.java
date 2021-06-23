package com.letsave.finance.mapper;
/*
    @Created: 20 / 06 / 2021 - 3:12 PM
    @Author: Dummy
*/

import com.letsave.finance.model.ProfileModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfileMapper {

  void insertProfile(ProfileModel profile);

  void updateProfile(ProfileModel profile);

  ProfileModel findProfileByColumn(@Param("column") String column, @Param("value") String value);

}
