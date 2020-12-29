package com.robin.sys.dao;

import com.robin.sys.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id} and power = #{power}")
    User getById(@Param("id") int id, int power);
    @Select("select * from user where id = #{id}")
    User getByIdWithOutPower(@Param("id") int id);
    @Select("select * from user where number = #{number} and power = #{power}")
    User getByNumber(@Param("number") String number, int power);
    @Insert("insert into user(name,sex,email,clazz,number,password,power,salt,register_date,last_login_date) values(#{name},#{sex},#{email},#{clazz},#{number},#{password},#{power},#{salt},#{registerDate},#{lastLoginDate})")
    int insertUser(User user);
    @Update("update user set password = #{password} where id = #{id}")
    void updatePwdById(User user);
    @Update("update user set password = #{password} where number = #{number}")
    void updatePwdByNumber(User user);
    @Update("update user set last_login_date = #{lastLoginDate} where id = #{id}")
    void updateLastLoginDateById(User user);
    @Update("update user set last_login_date = #{lastLoginDate} where number = #{number}")
    void updateLastLoginDateByNumber(User user);
    @Update("update user set name = #{name},sex = #{sex},clazz = #{clazz},email = #{email} where id = #{id}")
    void updateUserInfoById(User user);
    @Update("update user set password = #{password} where id = #{id}")
    void changePasswordById(User user);
    @Select("select * from user where power = 3")
    List<User> listAdmin();
    @Select("select * from user where power = 2")
    List<User> listTeacher();
    @Select("select * from user where power = 1")
    List<User> listStudent();
    @Delete("delete from user where id = #{id}")
    int deleteUserById(int id);
    @Select("select * from user where id = #{id}")
    User getUserById(int id);
}
