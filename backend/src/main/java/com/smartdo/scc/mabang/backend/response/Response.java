package com.smartdo.scc.mabang.backend.response;

import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Data;

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
}
