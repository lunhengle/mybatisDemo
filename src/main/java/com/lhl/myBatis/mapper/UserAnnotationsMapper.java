package com.lhl.myBatis.mapper;

import com.lhl.myBatis.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lunhengle on 2015/8/13.
 */
public interface UserAnnotationsMapper {
    @Insert("insert into user(username,password,phone) values (#{username},#{password},#{phone})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertUser(User user);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(User user);

    @Select("select * from user where id=#{id}")
    User selectUserById(User user);

    @Select("select * from user")
    List<User> selectUserList();
}
