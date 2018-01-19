package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.ProductPurchaseInfo;
import com.smartdo.scc.mabang.backend.bean.PurchaseDetail;
import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ProductPurchaseInfoResponse extends Response {
    @Getter
    private List<ProductPurchaseInfo> productPurchaseInfoList = new ArrayList<ProductPurchaseInfo>();
    @Getter
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<PurchaseDetail>();

    public ProductPurchaseInfoResponse(HttpResult result) throws HttpClientError {
        super(result);
        setBeans();
    }


    @Override
    public void setBeans() throws HttpClientError {
        if (result.getCode() == 200) {
            JSONObject object = JSON.parseObject(this.result.getBody());
            System.out.println(object);
            this.setCode(object.getString("code"));
            this.setMessage(object.getString("message"));
            this.setDataCount(object.getInteger("dataCount"));
            this.setPageCount(object.getInteger("pageCount"));
            if (object.getString("code").equals("000")) {
                JSONArray dataArray = object.getJSONArray("data");
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject singeObj = dataArray.getJSONObject(i);
                    ProductPurchaseInfo entity = JSON.parseObject(JSON.toJSONString(singeObj), ProductPurchaseInfo.class);
                    productPurchaseInfoList.add(entity);
                    JSONArray purchaseDetailArray = singeObj.getJSONArray("PurchaseDetail");
                    String groupIdStr = singeObj.getString("groupId");
                    for (int j = 0; j < purchaseDetailArray.size(); j++) {
                        Object purchaseDetailObj = purchaseDetailArray.get(j);
                        PurchaseDetail purchaseDetailEntity = JSON.parseObject(JSON.toJSONString(purchaseDetailObj), PurchaseDetail.class);
                        purchaseDetailEntity.setGroupId(groupIdStr);
                        purchaseDetailList.add(purchaseDetailEntity);
                    }
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
