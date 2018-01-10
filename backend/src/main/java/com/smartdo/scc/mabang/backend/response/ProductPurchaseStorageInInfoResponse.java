package com.smartdo.scc.mabang.backend.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import com.smartdo.scc.mabang.common.helper.HttpResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ProductPurchaseStorageInInfoResponse extends Response{
    @Getter
    private List<ProductPurchaseStorageInInfo> productList = new ArrayList<ProductPurchaseStorageInInfo>();
    @Setter
    private String purchaseGroups ;

    public ProductPurchaseStorageInInfoResponse(HttpResult result, String purchaseGroups) {
        super(result);
        this.purchaseGroups = purchaseGroups;
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
                    JSONArray dataArray = object.getJSONArray("data");
                    for (int i = 0; i < dataArray.size(); i++) {
                        JSONObject singeObj=dataArray.getJSONObject(i);
                        String purchaseGroup = singeObj.getString("purchaseGroup");
                        JSONArray entityArray = singeObj.getJSONArray("PurchaseDetail");
                        for (int j = 0; j < entityArray.size(); j++) {
                            Object productPurchaseStorageInInfo = entityArray.get(j);
                            ProductPurchaseStorageInInfo entity = JSON.parseObject(JSON.toJSONString(productPurchaseStorageInInfo), ProductPurchaseStorageInInfo.class);
                            entity.setPurchaseGroup(purchaseGroup);
                            productList.add(entity);
                        }
                    }
                }else {
                   System.out.println("查询结果为为：" +object);
                }
            }else{
                System.out.println("查询结果为：" +object);
            }
        } else {
            System.out.println("请求出错" + result.getCode());
        }
    }
}
