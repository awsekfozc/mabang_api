package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockProviderInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StockProviderInfoResponse extends Response{
    @Getter
    private List<StockProviderInfo> productList = new ArrayList<StockProviderInfo>();
    @Setter
    private String stockIds ;

    public StockProviderInfoResponse(HttpResult result, String stockIds) {
        super(result);
        this.stockIds = stockIds;
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            System.out.println(object);
            System.out.println("==================================");
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            if (object.getString("code").equals("000")){
                String bb = object.getString("data");
                if(!bb.equals("[]")){
                    JSONObject dataObj = object.getJSONObject("data");
                    System.out.println(dataObj);
                    String str = this.stockIds;
                    String[] stockIdsArry = str.split(",");
                    for (int i = 0; i <stockIdsArry.length ; i++) {
                        JSONObject singleObj = dataObj.getJSONObject(stockIdsArry[i]);
                        if(singleObj != null){
                            StockProviderInfo  entity = JSON.parseObject(JSON.toJSONString(singleObj), StockProviderInfo.class);
                            entity.setStockIds(stockIdsArry[i]);
                            System.out.println(entity);
                            productList.add(entity);
                        }
                    }
                }else {
                   System.out.println("查询结果为：" +object);
                }
            }else{
                System.out.println(object.getString("message"));
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
