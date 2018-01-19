package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StockProviderInfoResponse extends Response {
    @Getter
    private List<StockProviderInfo> stockProviderInfoList = new ArrayList<StockProviderInfo>();
    @Setter
    private String stockIds;

    public StockProviderInfoResponse(HttpResult result, String stockIds) throws HttpClientError {
        super(result);
        this.stockIds = stockIds;
        setBeans();
    }


    @Override
    public void setBeans() throws HttpClientError{
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            if (object.getString("code").equals("000")) {
                JSONArray dataArray = object.getJSONArray("data");
                for (Object obj : dataArray) {
                    stockProviderInfoList.add(JSON.parseObject(JSON.toJSONString(obj), StockProviderInfo.class));
                }
            } else {
                log.warn("查询结果为：" + object);
                throw new HttpClientError("查询结果为：" + object);
            }
        } else {
            log.warn("请求出错" + result.getCode());
            throw new HttpClientError("请求出错" + result.getCode());
        }
    }
}
