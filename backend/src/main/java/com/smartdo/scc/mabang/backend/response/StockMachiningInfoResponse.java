package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StockMachiningInfoResponse extends Response {
    @Getter
    private List<StockMachiningInfo> productList = new ArrayList<StockMachiningInfo>();
    @Setter
    private String stockIds;

    public StockMachiningInfoResponse(HttpResult result, String stockIds) {
        super(result);
        this.stockIds = stockIds;
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            if (object.getString("code").equals("000")) {
                String bb = object.getString("data");
                if (!bb.equals("[]")) {
                    JSONObject dataObj = object.getJSONObject("data");
                    String str = this.stockIds;
                    String[] stockIdsArry = str.split(",");
                    for (int i = 0; i < stockIdsArry.length; i++) {
                        JSONArray singleArray = dataObj.getJSONArray(stockIdsArry[i]);
                        if (singleArray != null) {
                            for (Object stockMachiningInfo : singleArray) {
                                StockMachiningInfo entity = JSON.parseObject(JSON.toJSONString(stockMachiningInfo), StockMachiningInfo.class);
                                entity.setStockIds(stockIdsArry[i]);
                                System.out.println(entity);
                                productList.add(entity);
                            }
                        }
                    }
                } else {
                    System.out.println("查询结果为" + object);
                }
            } else {
                System.out.println("查询结果为" +object);
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
