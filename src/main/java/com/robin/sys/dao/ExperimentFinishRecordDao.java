package com.robin.sys.dao;

import com.robin.sys.domain.ExperimentFinishRecordView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExperimentFinishRecordDao {
    @Select("select e.id as id,d.id as expriment_id,d.number as experiment_number,d.name as experiment_name,d.task as experiment_task,d.create_date,d.teacher_name,e.preview,e.report,e.total_score from (select c.id,c.number,c.name,c.content,c.task,c.create_date,user.name as teacher_name from (select a.id,a.number,a.name,a.content,a.task,b.create_date,b.teacher_id from (select id,number,name,content,task from experiment where id in(select experiment_id from experiment_record where clazz_name = #{clazz})) a left join experiment_record b on a.id = b.experiment_id) c left join user on c.teacher_id = user.id) d left join experiment_finish_record e on d.id = e.experiment_id and e.student_id = #{id}")
    List<ExperimentFinishRecordView> listExperimentFinishRecordViewForStudent(int id, String clazz);
}
