package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockWarehouseInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品响应类
 */
public class StockWarehouseInfoResponse extends Response {

    @Getter
    private List<StockWarehouseInfo> productList = new ArrayList<StockWarehouseInfo>();

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
            JSONArray array = object.getJSONArray("data");

            if(object.getString("code").equals("000")){
                for(Object stockWarehouseInfo:array){
                    System.out.println(stockWarehouseInfo);
                    productList.add(JSON.parseObject(JSON.toJSONString(stockWarehouseInfo), StockWarehouseInfo.class));
                }
            }else {
                System.out.println(object.getString("message"));
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
