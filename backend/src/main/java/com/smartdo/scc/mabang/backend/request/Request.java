package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import com.smartdo.scc.mabang.common.helper.DateUtils;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

public abstract class Request {

    protected String version = "v2017";
    protected String developerId = "100197";
    protected String authToken = "eeb10fba7b268006b2f5febe2bd839e4";
    protected String action;
    protected String url = "http://api-private.wms.mabangerp.com";
    protected long timestemp = System.currentTimeMillis() / 1000L;
    @Setter
    @Getter
    private HttpResult result;

    public Request(String action) {
        this.action = action;
    }

    public String getPublicUrl() {
        return this.url + String.format("?version=%s&timestamp=%s&developerId=%s&authToken=%s&action=%s", version, timestemp + "", developerId, authToken, action);
    }

    public abstract String stitchingRequest() throws IncorrectParametersError;
}
