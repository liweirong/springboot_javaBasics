package com.iris.mapper;

import com.iris.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserById(Integer id);

    List<User> getUserList(String name, int page, int limit);

    List<User> getAllUser();

    int add(User user);

    int update(User user);

    int delete(Integer id);

    int getUserCount();

    List<User> getUserByAccount(String account);
}