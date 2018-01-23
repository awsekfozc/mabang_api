package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.request.Request;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ProductPurchaseStorageInInfoResponse extends Response{
    @Getter
    private List<ProductPurchaseStorageInInfo> entityList = new ArrayList<ProductPurchaseStorageInInfo>();
    @Setter
    private String purchaseGroups ;

    public ProductPurchaseStorageInInfoResponse(HttpResult result, String purchaseGroups, Request r) throws HttpClientError {
        super(result);
        this.purchaseGroups = purchaseGroups;
        setBeans();

    }


    @Override
    public void setBeans()throws  HttpClientError{
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            if (object.getString("code").equals("000")){
                JSONArray dataArray = object.getJSONArray("data");
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject singeObj=dataArray.getJSONObject(i);
                    Integer purchaseGroup = singeObj.getInteger("purchaseGroup");
                    JSONArray entityArray = singeObj.getJSONArray("PurchaseDetail");
                    for (int j = 0; j < entityArray.size(); j++) {
                        Object productPurchaseStorageInInfo = entityArray.get(j);
                        ProductPurchaseStorageInInfo entity = JSON.parseObject(JSON.toJSONString(productPurchaseStorageInInfo), ProductPurchaseStorageInInfo.class);
                        entity.setPurchaseGroup(purchaseGroup);
                        entityList.add(entity);
                    }
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
