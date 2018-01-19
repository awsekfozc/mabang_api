package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.request.StockWarehouseInfoRequest;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品响应类
 */
@Slf4j
public class StockWarehouseInfoResponse extends Response {

    @Getter
    private List<StockWarehouseInfo> stockWarehouseInfoList = new ArrayList<StockWarehouseInfo>();

    public StockWarehouseInfoResponse(HttpResult result,Request r) throws HttpClientError {

        super(result);
        this.updateTimeStart = ((StockWarehouseInfoRequest)r).getUpdateTimeStart();
        this.updateTimeEnd = ((StockWarehouseInfoRequest)r).getUpdateTimeEnd();
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
            if(object.getString("code").equals("000")){
                JSONArray array = object.getJSONArray("data");
                for(Object stockWarehouseInfo:array){
                    StockWarehouseInfo entity = JSON.parseObject(JSON.toJSONString(stockWarehouseInfo), StockWarehouseInfo.class);
                    entity.setUpdateTimeEnd(this.updateTimeEnd);
                    stockWarehouseInfoList.add(entity);
                }
            }else {
                log.warn("查询结果为：" +object);
                throw new HttpClientError("查询结果为：" + object);
            }
        } else {
            log.warn("请求出错" + result.getCode());
            throw new HttpClientError("请求出错" + result.getCode());
        }
    }
}
