package com.lee.homework.mapper;

import com.lee.homework.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<String> selectMenusByUsername(String username);

    String selectMenuNameByChinese(String menuChineseName);

}