package com.smartdo.scc.mabang.backend.bean;

import lombok.Data;

import java.util.Date;
/**
 * 数据Entity类
 */
@Data
public class DataEntity {
    protected String remarks;	// 备注
    protected Date createDate;	// 本地创建日期
    protected Date updateDate;	// 本地更新日期
    protected Date updateTimeStart; // 	 	最后更新开始时间参数(本地确定时间区间条件查询)
    protected Date updateTimeEnd; // 	 	最后更新结束时间参数(本地确定时间区间条件查询)
    protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
}
