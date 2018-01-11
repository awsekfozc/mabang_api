package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
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

    public StockWarehouseInfoResponse(HttpResult result) {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            if(object.getString("code").equals("000")){
                JSONArray array = object.getJSONArray("data");
                for(Object stockWarehouseInfo:array){
                    System.out.println(stockWarehouseInfo);
                    stockWarehouseInfoList.add(JSON.parseObject(JSON.toJSONString(stockWarehouseInfo), StockWarehouseInfo.class));
                }
            }else {
                System.out.println("查询结果为：" +object);
                log.warn("查询结果为：" +object);
            }
        } else {
            System.out.println("请求出错" + result.getCode());
            log.warn("请求出错" + result.getCode());
        }
    }
}
