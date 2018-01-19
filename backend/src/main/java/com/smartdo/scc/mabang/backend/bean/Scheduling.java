package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Scheduling {
    protected Date updateTimeStart; // 	 	最后更新开始时间(本地确定时间区间条件查询，不一定等同于第三方的更新区间)
    protected Date updateTimeEnd; // 	 	最后更新结束时间(本地确定时间区间条件查询，不一定等同于第三方的更新区间)
}
