package com.robin.sys.dao;

import com.robin.sys.domain.DeviceUsageRecord;
import com.robin.sys.domain.DeviceUsageRecordView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceUsageRecordDao {
    @Select("select r.id as id,r.device_name as device_name,r.device_model as device_model,r.device_number as device_number,r.user_name as user_name,r.user_number as user_number,r.power as power,r.start_date as start_date,r.end_date as end_date,r.target as target,r.create_date as create_date,r.status as status,u.name as reviewer_name,u.number as reviewer_number,r.device_status as device_status,r.usage_status as usage_status from (select l.id as id,l.reviewer_id as reviewer_id,u.power as power,device_name,device_model,device_number,device_status,usage_status,start_date,end_date,target,create_date,status,u.name as user_name,u.number as user_number from (select duc.id as id,duc.user_id as user_id,duc.reviewer_id as reviewer_id,d.name as device_name,d.model as device_model,d.number as device_number,d.device_status as device_status,d.usage_status as usage_status,duc.start_date as start_date,duc.end_date as end_date,duc.target as target,duc.create_date as create_date,duc.status as status from device_usage_record duc left join device d on duc.device_id = d.id) l left join user u on l.user_id = u.id) r left join user u on r.reviewer_id = u.id")
    List<DeviceUsageRecordView> listDeviceUsageRecord();
}
