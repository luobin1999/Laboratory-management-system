package com.robin.sys.dao;

import com.robin.sys.domain.Laboratory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LaboratoryDao {
    @Select("select * from laboratory")
    List<Laboratory> listLaboratory();
    @Insert("insert into laboratory(name,campus,admin,admin_number,content,create_date) values(#{name},#{campus},#{admin},#{adminNumber},#{content},#{createDate})")
    int insertLaboratory(Laboratory laboratory);
    @Select("select * from laboratory where id = #{id}")
    Laboratory getLaboratoryById(int id);
    @Update("update laboratory set name = #{name},campus = #{campus},admin = #{admin},admin_number = #{adminNumber},update_date = #{updateDate},content = #{content} where id = #{id}")
    int updateLaboratoryById(Laboratory laboratory);
    @Delete("delete from laboratory where id = #{id}")
    int deleteLaboratoryById(int id);
}
