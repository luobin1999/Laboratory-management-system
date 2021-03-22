package com.robin.sys.dao;

import com.robin.sys.domain.ExperimentFinishRecord;
import com.robin.sys.domain.view.ExperimentFinishRecordView;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExperimentFinishRecordDao {
    @Select("select e.id as id,d.id as experiment_id,d.user_name as student_name,d.user_number as student_number,d.number as experiment_number,d.name as experiment_name,d.task as experiment_task,d.create_date,d.teacher_name,e.preview,e.report,e.total_score,e.preview_date,e.report_date,e.preview_score,e.report_score from (select * from (select c.id,c.user_id,c.user_name,c.user_number,c.number,c.name,c.content,c.task,c.create_date,user.name as teacher_name from (select a.id,b.user_id,b.user_name,b.user_number,a.number,a.name,a.content,a.task,b.create_date,b.teacher_id from experiment a right join (select user.id as user_id,user.name as user_name,user.number as user_number,x.experiment_id,x.create_date,x.teacher_id from (select id,name,number,clazz from user where clazz = #{clazzName}) user join experiment_record x on user.clazz = x.clazz_name) b on a.id = b.experiment_id) c left join user on c.teacher_id = user.id) d where user_id = #{userId}) d left join experiment_finish_record e on e.student_id = d.user_id and d.id = e.experiment_id")
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
    @Select("select f.user_id as student_id,f.user_name as student_name,clazz_name,f.user_number as student_number,f.experiment_id,experiment_name,experiment_number,experiment_task,f.create_date,teacher_name,preview,preview_date,preview_score,report,report_date,report_score,total_score from (select user_id,user_name,user_number,clazz_name,experiment_id,experiment_name,experiment_number,experiment_task,d.create_date,e.name as teacher_name from (select user_id,user_name,user_number,clazz_name,experiment_id,c.name as experiment_name,c.number as experiment_number,c.task as experiment_task,b.create_date,teacher_id from (select user_id,user_name,user_number,clazz_name,experiment_id,create_date,teacher_id from (select user.id as user_id,user.name as user_name,user.number as user_number,user.clazz as clazz_name,x.experiment_id,x.create_date,x.teacher_id from (select id,name,number,clazz from user where clazz = #{clazzName}) user left join experiment_record x on user.clazz = x.clazz_name) a where a.experiment_id = #{experimentId}) b left join experiment c on c.id = b.experiment_id) d left join user e on e.id = d.teacher_id) f left join experiment_finish_record g on f.experiment_id = g.experiment_id and f.user_id = g.student_id order by student_number")
    List<ExperimentFinishRecordView> listExperimentTaskByClazzName(int experimentId, String clazzName);
    @Update("update experiment_finish_record set preview_score = #{previewScore},total_score = #{totalScore} where id = #{id}")
    void updatePreviewScoreById(ExperimentFinishRecord experimentFinishRecord);
    @Update("update experiment_finish_record set report_score = #{reportScore},total_score = #{totalScore} where id = #{id}")
    void updateReportScoreById(ExperimentFinishRecord experimentFinishRecord);
    @Insert("insert into experiment_finish_record(student_id,experiment_id,experiment_record_id,preview_score,total_score) values(#{studentId},#{experimentId},#{experimentRecordId},#{previewScore},#{totalScore})")
    void insertExperimentFinishRecordPreviewScore(ExperimentFinishRecord experimentFinishRecord);
    @Insert("insert into experiment_finish_record(student_id,experiment_id,experiment_record_id,report_score,total_score) values(#{studentId},#{experimentId},#{experimentRecordId},#{reportScore},#{totalScore})")
    void insertExperimentFinishRecordReportScore(ExperimentFinishRecord experimentFinishRecord);
}
