package com.mmc.springbootdemo.dao.mybatis;

import com.mmc.springbootdemo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("insert into users(name, age) values(#{name}, #{age})")
    void insert(@Param("name") String name, @Param("age") Integer age);

    @Select("select * from users")
    List<User> getAllUsers();

    @Insert("<script>"  +
            "insert into users(name, age) VALUES" +
            "<foreach collection=\"user_list\" item=\"iter_user\" index=\"index\"  separator=\",\">" +
            "(#{iter_user.name},#{iter_user.age})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("user_list") List<User> user_list);
}