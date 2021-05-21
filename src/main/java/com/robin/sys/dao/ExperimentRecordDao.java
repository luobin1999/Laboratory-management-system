package com.robin.sys.dao;

import com.robin.sys.domain.ExperimentRecord;
import com.robin.sys.domain.view.ExperimentClazzView;
import com.robin.sys.domain.view.ExperimentRecordView;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExperimentRecordDao {
    @Select("select id,experiment_id,clazz_name,teacher_id,create_date from experiment_record where experiment_id = #{experimentId} and clazz_name = #{clazzName}")
    ExperimentRecord getExperimentRecord(int experimentId, String clazzName);
    @Insert("insert into experiment_record(experiment_id,clazz_name,teacher_id,create_date) values(#{experimentId},#{clazzName},#{teacherId},#{createDate})")
    void insertExperimentRecord(ExperimentRecord experimentRecord);
    @Select("select b.id as id,b.experiment_id as experiment_id,a.number as experiment_number,a.name as experiment_name from experiment a right join (select id,experiment_id from experiment_record group by experiment_id) b on a.id = b.experiment_id")
    List<ExperimentRecordView> listExperimentRecordView();
    @Select("select e.id,experiment_id,experiment_number,experiment_name,create_date,clazz_name,teacher_name,f.count,e.is_group from (select c.id,experiment_id,experiment_number,experiment_name,c.create_date,clazz_name,d.name as teacher_name,c.is_group from (select a.id,a.experiment_id,b.number as experiment_number,b.name as experiment_name,a.create_date,a.clazz_name,a.teacher_id,a.is_group from (select id,experiment_id,create_date,clazz_name,teacher_id,is_group from experiment_record where experiment_id = #{experimentId}) a left join experiment b on a.experiment_id = b.id ) c left join user d on c.teacher_id = d.id) e left join (select c.clazz as clazz,count(clazz) as count from (select c.clazz from user u right join class c on u.clazz = c.clazz) as c group by c.clazz) f on e.clazz_name = f.clazz order by clazz_name")
    List<ExperimentClazzView> listClazzForExperiment(Integer experimentId);
    @Delete("delete from experiment_record where experiment_id = #{experimentId}")
    void deleteExperimentRecord(int experimentId);
    @Update("update experiment_record set is_group=1 where experiment_id=#{experimentId} and clazz_name=#{clazzName}")
    void updateIsGroupStatus(Integer experimentId, String clazzName);
    @Select("select * from experiment_record where  experiment_id=#{experimentId} and clazz_name=#{clazzName}")
    ExperimentRecord getExperimentRecordByClazzNameAndExperimentId(String clazzName, Integer experimentId);
}
