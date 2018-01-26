package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品响应类
 */
@Slf4j
public class StockWarehouseInfoResponse extends Response {

    @Getter
    private List<StockWarehouseInfo> stockWarehouseInfoList = new ArrayList<StockWarehouseInfo>();
    @Getter
    private Map<String,List>  resultMap =  new HashMap<>();

    public StockWarehouseInfoResponse(HttpResult result, Request r) throws HttpClientError {

        super(result);
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
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    StockWarehouseInfo stockWarehouseInfo = JSON.parseObject(JSON.toJSONString(jsonObject), StockWarehouseInfo.class);
                    String stockId = jsonObject.getString("stockId");
                    List list = resultMap.get(stockId);
                    if(list == null){
                        list = new ArrayList();
                        list.add(stockWarehouseInfo);
                    }else{
                        list.add(stockWarehouseInfo);
                    }
                    resultMap.put(stockId,list);
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
