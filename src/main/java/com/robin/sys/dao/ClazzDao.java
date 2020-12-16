package com.robin.sys.dao;

import com.robin.sys.domain.Clazz;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzDao {

    @Select("select c.id,c.grade as grade,c.clazz as clazz,count(clazz) as count,c.update_date as update_date,c.create_date as create_date from (select c.id,c.grade,c.clazz,c.update_date,c.create_date from user u right join class c on u.clazz = c.clazz) as c group by c.clazz order by c.grade desc")
    List<Clazz> listClazz();
    @Select("select * from class where id = #{id}")
    Clazz getClassById(int id);
    @Select("select * from class where clazz = #{clazz}")
    Clazz getClassByClazz(String clazz);
    @Update("update class set grade = #{grade}, clazz = #{clazz}, update_date = #{updateDate} where id = #{id}")
    void updateClassById(Clazz clazz);
    @Insert("insert into class(grade,clazz,create_date) values(#{grade},#{clazz},#{createDate})")
    int insertClass(Clazz clazz);
    @Delete("delete from class where id = #{id}")
    int deleteClass(int id);
}
