package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISchedulingDao {
    @Select(" select  update_time_start AS updateTimeStart,update_time_end AS updateTimeEnd , interface_code AS interfaceCode from Scheduling where interface_code = #{interfaceCode} and is_success = '0' order by id  desc limit 0,1;")
    List<Scheduling> getUpdateTime (Scheduling scheduling);

    @Insert(" INSERT INTO Scheduling (update_time_start,update_time_end,interface_code,is_success)" +
            " VALUES (#{updateTimeStart},#{updateTimeEnd},#{interfaceCode},#{isSuccess});")
    int add(Scheduling scheduling);
}
