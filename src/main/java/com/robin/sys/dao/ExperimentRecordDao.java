package com.robin.sys.dao;

import com.robin.sys.domain.ExperimentRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExperimentRecordDao {
    @Select("select * from experiment_record where experiment_id = #{experimentId} and clazz_name = #{clazzName}")
    ExperimentRecord getExperimentRecord(int experimentId, String clazzName);
}
