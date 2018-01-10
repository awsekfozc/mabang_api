package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Data;

import java.util.Map;

@Data
public abstract class Response {

    protected String code;
    protected String message;
    protected Integer pageCount;
    protected Integer dataCount;
    protected String data;
    protected HttpResult result;

    public Response(HttpResult result) {
        this.result = result;
    }

    public abstract void setBeans();

    public boolean checkResultCode() {
        Map<String, Object> bodyMap = JSON.parseObject(result.getBody(), Map.class);
        if (bodyMap.get("code").equals("000") && result.getCode() == 200) {
            return true;
        }
        return false;
    }
}
