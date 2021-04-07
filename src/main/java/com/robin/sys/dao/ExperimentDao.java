package com.robin.sys.dao;

import com.robin.sys.domain.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExperimentDao {
    @Select("select * from experiment")
    List<Experiment> listExperiment();
    @Select("select * from experiment where id in(select experiment_id from experiment_record where clazz_name = #{clazz})")
    List<Experiment> listExperimentForStudent(String clazz);
    @Insert("insert into experiment(name,number,content,task,create_date,nature) values(#{name},#{number},#{content},#{task},#{createDate},#{nature})")
    void insertExperiment(Experiment experiment);
    @Select("select * from experiment where id = #{id}")
    Experiment getExperimentById(int id);
    @Delete("delete from experiment where id = #{id}")
    void deleteExperimentById(int id);
    @Update("update experiment set name = #{name},number = #{number},content = #{content},task = #{task},nature = #{nature}  where id = #{id}")
    void updateExperiment(Experiment experiment);
    @Update("update experiment set name = #{name},number = #{number},content = #{content},nature = #{nature} where id = #{id}")
    void updateExperimentNotTask(Experiment experiment);
}
