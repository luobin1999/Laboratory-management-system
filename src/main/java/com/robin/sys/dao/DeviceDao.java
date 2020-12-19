package com.robin.sys.dao;

import com.robin.sys.domain.Device;
import com.robin.sys.domain.DeviceOverview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeviceDao {
    @Select("select * from device")
    List<Device> listDevice();
    @Select("select name,model,count(if(device_status = 0,true,null)) as normal_count,count(if(device_status = 1,true,null)) as wait_repair_count,count(if(device_status = 2,true,null)) as already_repair_count,count(if(device_status = 3,true,null)) as scrap_count,count(if(usage_status = 0,true,null)) as free_count,count(if(usage_status = 1,true,null)) as usage_count,check_date,admin from device group by name,model")
    List<DeviceOverview> listDeviceOverview();
    @Insert("insert into device(name,model,number,admin,buy_date,create_date,device_status,usage_status) values(#{name},#{model},#{number},#{admin},#{buyDate},#{createDate},#{deviceStatus},#{usageStatus})")
    int insertDevice(Device device);
    @Select("select * from device where id = #{id}")
    Device getDeviceById(int id);
    @Update("update device set name = #{name},model = #{model}, number = #{number}, admin = #{admin}, update_date = #{updateDate}, device_status = #{deviceStatus}, usage_status = #{usageStatus} where id = #{id}")
    int updateDeviceById(Device device);
}
