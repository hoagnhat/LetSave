package com.letsave.finance.mapper;
/*
    @Created: 20 / 06 / 2021 - 8:18 PM
    @Author: Dummy
*/

import com.letsave.finance.model.CategoryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

  CategoryModel findCategoryByColumn(@Param("column") String column, @Param("value") String value);

  List<CategoryModel> findCategoriesByAccountId(@Param("accountId") long accountId);

  void insertCategory(CategoryModel categoryModel);

}
