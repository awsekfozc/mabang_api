package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Data
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

    protected String SplicingParameters(Map map){
        StringBuilder realPara = new StringBuilder();
        for (Object key : map.keySet()) {
            if (map.get(key) != null){
                realPara.append("&"+ key + "=" + map.get(key));
            }
        }
        return realPara.toString();
    }
    protected boolean checkLength(String stockIds){
        String[] stockIdsArry = stockIds.split(",");
        if (stockIdsArry.length>10){
            return false;
        }else {
            return true;
        }
    }
    protected boolean checkIsEmpty(String str){
        if (str.trim().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public abstract String stitchingRequest() throws IncorrectParametersError;
}
