package com.smartdo.scc.mabang.backend.response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品响应类
 */
@Slf4j
public class StockInfoActionResponse extends Response {

    @Getter
    private List<StockInfo> stockInfoList = new ArrayList<>();


    public StockInfoActionResponse(HttpResult result) {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            this.setCode(object.getString("code"));
            JSONArray array = object.getJSONArray("data");
            if(object.getString("code").equals("000")){
                for(Object product:array){
                    System.out.println(product);
                    stockInfoList.add(JSON.parseObject(JSON.toJSONString(product), StockInfo.class));
                }
            }else{
                System.out.println("查询结果为：" +object);
                log.warn("查询结果为：" +object);
            }
        } else {
            System.out.println("请求出错" + result.getCode());
            log.warn("请求出错" + result.getCode());
        }
    }
}
