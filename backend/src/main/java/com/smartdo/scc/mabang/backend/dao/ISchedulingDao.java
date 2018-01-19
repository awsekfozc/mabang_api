package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.Scheduling;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISchedulingDao {
    @Select(" select  update_time_start AS updateTimeStart,update_time_end AS updateTimeEnd from Scheduling order by id  desc limit 0,1;")
    List<Scheduling> getUpdateTime ();

    @Insert(" INSERT INTO Scheduling (update_time_start,update_time_end)" +
            " VALUES (#{updateTimeStart},#{updateTimeEnd});")
    int add(Scheduling scheduling);
}
