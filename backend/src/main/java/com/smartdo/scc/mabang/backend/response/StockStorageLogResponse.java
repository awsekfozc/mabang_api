package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockStorageLog;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品响应类
 */
@Slf4j
public class StockStorageLogResponse extends Response {

    @Getter
    private List<StockStorageLog> stockStorageLogList = new ArrayList<StockStorageLog>();


    public StockStorageLogResponse(HttpResult result) throws HttpClientError {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() throws HttpClientError

    {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            this.setCode(object.getString("code"));
            JSONArray array = object.getJSONArray("data");
            if(object.getString("code").equals("000")){
                for(Object obj:array){
                    stockStorageLogList.add(JSON.parseObject(JSON.toJSONString(obj), StockStorageLog.class));
                }
            }else{
                log.warn("查询结果为：" +object);
                throw new HttpClientError("查询结果为：" + object);
            }
        } else {
            log.warn("请求出错" + result.getCode());
            throw new HttpClientError("请求出错" + result.getCode());
        }
    }
}
