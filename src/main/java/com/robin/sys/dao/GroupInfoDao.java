package com.robin.sys.dao;

import com.robin.sys.domain.GroupInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupInfoDao {
    @Delete("delete from group_info where clazz_name=#{clazzName} and experiment_name=#{experimentName}")
    void deleteGroupInfoByClazzNameAndExperimentName(String clazzName, String experimentName);
    @Insert("insert into group_info(student_number, student_name, clazz_name, experiment_name, group_id, is_leader) values(#{studentNumber}, #{studentName}, #{clazzName}, #{experimentName}, #{groupId}, #{isLeader})")
    void insertGroupInfo(GroupInfo groupInfo);
}
