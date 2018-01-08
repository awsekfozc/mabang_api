package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

@Data
public class ProductPurchaseStorageInInfoRequest extends Request {
    private static String myAction = "get-stock-machining-info-data";
    private String purchaseGroups ;
    private String urlFormat = "&purchaseGroups=%s";

    public ProductPurchaseStorageInInfoRequest() {
        this(myAction);
    }
    private ProductPurchaseStorageInInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {

        //要检验！！！！！！！！！！！！！！！！！！！！！！
        if(purchaseGroups == null ){
            System.out.println("ProductPurchaseStorageInInfoRequest必须需要purchaseGroups参数");
            System.out.println("ProductPurchaseStorageInInfoRequest必须需要purchaseGroups参数");
            System.out.println("ProductPurchaseStorageInInfoRequest必须需要purchaseGroups参数");
            return null;
        }else{
            return super.getPublicUrl() + String.format(urlFormat, purchaseGroups);
        }
    }

}
