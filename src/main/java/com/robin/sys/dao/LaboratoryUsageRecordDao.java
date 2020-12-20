package com.robin.sys.dao;

import com.robin.sys.domain.LaboratoryRecord;
import com.robin.sys.domain.LaboratoryUsageRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LaboratoryUsageRecordDao {
    @Select("select r.id as id,r.campus as campus,r.laboratory_name as laboratory_name,r.user_name as user_name,r.user_number as user_number,r.power as power,r.start_date as start_date,r.end_date as end_date,r.target as target,r.create_date as create_date,r.status as status,u.name as reviewer_name,u.number as reviewer_number,r.laboratory_status as laboratory_status from (select l.id as id,l.campus as campus,l.reviewer_id as reviewer_id,u.power as power,laboratory_name,laboratory_status,start_date,end_date,target,create_date,status,u.name as user_name,u.number as user_number from (select luc.id as id,l.campus as campus,luc.user_id as user_id,luc.reviewer_id as reviewer_id,l.name as laboratory_name,l.is_free as laboratory_status,luc.start_date as start_date,luc.end_date as end_date,luc.target as target,luc.create_date as create_date,luc.status as status from laboratory_usage_record luc left join laboratory l on luc.laboratory_id = l.id) l left join user u on l.user_id = u.id) r left join user u on r.reviewer_id = u.id")
    List<LaboratoryUsageRecord> listLaboratoryUsageRecord();
    @Select("select * from laboratory_usage_record where laboratory_id = #{laboratoryId} and status = '2'")
    List<LaboratoryRecord> listLaboratoryUsageRecordByLaboratoryId(int laboratoryId);
    @Select("insert into laboratory_usage_record(laboratory_id,user_id,start_date,end_date,target,status,create_date) values(#{laboratoryId},#{userId},#{startDate},#{endDate},#{target},#{status},#{createDate})")
    void insertLaboratoryUsageRecord(LaboratoryRecord laboratoryRecord);
}
