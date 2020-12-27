package com.robin.sys.dao;

import com.robin.sys.domain.ExperimentFinishRecord;
import com.robin.sys.domain.ExperimentFinishRecordView;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExperimentFinishRecordDao {
    @Select("select e.id as id,d.id as expriment_id,d.user_name as student_name,d.user_number as student_number,d.number as experiment_number,d.name as experiment_name,d.task as experiment_task,d.create_date,d.teacher_name,e.preview,e.report,e.total_score,e.preview_date,e.report_date,e.preview_score,e.report_score from (select * from (select c.id,c.user_id,c.user_name,c.user_number,c.number,c.name,c.content,c.task,c.create_date,user.name as teacher_name from (select a.id,b.user_id,b.user_name,b.user_number,a.number,a.name,a.content,a.task,b.create_date,b.teacher_id from experiment a right join (select user.id as user_id,user.name as user_name,user.number as user_number,x.experiment_id,x.create_date,x.teacher_id from (select id,name,number,clazz from user where clazz = #{clazzName}) user left join experiment_record x on user.clazz = x.clazz_name) b on a.id = b.experiment_id) c left join user on c.teacher_id = user.id) d where user_id = #{userId}) d left join experiment_finish_record e on e.student_id = d.user_id and d.id = e.experiment_id")
    List<ExperimentFinishRecordView> listExperimentFinishRecordViewForStudent(int userId, String clazzName);
    @Select("select * from experiment_finish_record where experiment_id = #{experimentId} and student_id = #{studentId}")
    ExperimentFinishRecord getFinishRecordByExperimentIdAndStudentId(int experimentId, int studentId);
    @Update("update experiment_finish_record set preview = #{preview},preview_date = #{previewDate} where id = #{id}")
    void updatePreviewById(ExperimentFinishRecord experimentFinishRecord);
    @Update("update experiment_finish_record set report = #{report},report_date = #{reportDate} where id = #{id}")
    void updateReportById(ExperimentFinishRecord experimentFinishRecord);
    @Insert("insert into experiment_finish_record(experiment_id,student_id,experiment_record_id,preview,preview_date) values(#{experimentId},#{studentId},#{experimentRecordId},#{preview},#{previewDate})")
    void insertExperimentFinishRecordPreview(ExperimentFinishRecord experimentFinishRecord);
    @Insert("insert into experiment_finish_record(experiment_id,student_id,experiment_record_id,report,report_date) values(#{experimentId},#{studentId},#{experimentRecordId},#{report},#{reportDate})")
    void insertExperimentFinishRecordReport(ExperimentFinishRecord experimentFinishRecord);
}
