package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.StockMachiningInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.backend.request.StockMachiningInfoRequest;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class StockMachiningInfoResponse extends Response {
    @Getter
    private List<StockMachiningInfo> stockMachiningInfoList = new ArrayList<StockMachiningInfo>();
    @Setter
    private String stockIds;

    public StockMachiningInfoResponse(HttpResult result, String stockIds,Request r) throws HttpClientError {
        super(result);
        this.stockIds = stockIds;
        this.updateTimeStart = ((StockMachiningInfoRequest)r).getUpdateTimeStart();
        this.updateTimeEnd = ((StockMachiningInfoRequest)r).getUpdateTimeEnd();
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
                                entity.setUpdateTimeEnd(this.updateTimeEnd);
                                stockMachiningInfoList.add(entity);
                            }
                        }
                    }
                } else {
                    log.warn("查询结果为" + object);
                    throw new HttpClientError("查询结果为：" + object);
                }
            } else {
                log.warn("查询结果为" + object);
                throw new HttpClientError("查询结果为：" + object);
            }
        } else {
            log.warn("请求出错" + result.getCode());
            throw new HttpClientError("请求出错" + result.getCode());
        }
    }
}
