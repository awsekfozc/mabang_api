package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public abstract class Response {

    protected String code;
    protected String message;
    protected Integer pageCount;
    protected Integer dataCount;
    protected String data;
    protected HttpResult result;
    protected Date updateTimeStart; // 	 	最后更新开始时间(本地确定时间区间条件查询)
    protected Date updateTimeEnd; // 	 	最后更新结束时间(本地确定时间区间条件查询)

    public Response(HttpResult result) {
        this.result = result;
    }

    public abstract void setBeans() throws HttpClientError;

    public boolean checkResultCode() {
        Map<String, Object> bodyMap = JSON.parseObject(result.getBody(), Map.class);
        if (bodyMap.get("code").equals("000") && result.getCode() == 200) {
            return true;
        }
        return false;
    }
}
