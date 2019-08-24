package com.lee.homework.mapper;

import com.lee.homework.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectUserByUsername(String username);

    List<String> selectRoleByUsername(String username);

    List<String> selectPermissionByUsername(String username);
}