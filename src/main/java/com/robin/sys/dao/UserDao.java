package com.robin.sys.dao;

import com.robin.sys.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") long id);
    @Select("select * from user where number = #{number}")
    User getByNumber(@Param("number") String number);
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
}
