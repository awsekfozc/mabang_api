package com.smartdo.scc.mabang.backend.response;

import lombok.Data;

@Data
public abstract class Response {

    protected String code;
    protected String message;
    protected Integer pageCount;
    protected Integer dataCount;
    protected String data;

    public abstract void setBeans() ;
}
