package com.mmc.springbootdemo.dao.mybatis;

import com.mmc.springbootdemo.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AnnotationUserMapper {

    @Select("select * from users where name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("insert into users(name, age) values(#{name}, #{age})")
    void insert(@Param("name") String name, @Param("age") Integer age);

    @Select("select * from users")
    List<User> getAllUsers();

    @Insert("<script>"  +
            "insert into users(name, age) values" +
            "<foreach collection=\"userList\" item=\"iterUser\" index=\"index\"  separator=\",\">" +
            "(#{iterUser.name},#{iterUser.age})" +
            "</foreach>" +
            "</script>")
    void batchInsert(@Param("userList") List<User> userList);

    @Delete("delete from users where name = #{name}")
    void deleteUser(@Param("name") String name);

    @Update("update users set age = #{newAge} where name = #{conditionName}")
    void updateUser(@Param("conditionName") String conditionName, @Param("newAge") Integer newAge);

    @Select("truncate table users")
    void truncate();
}