package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

@Data
public class StockProviderInfoRequest extends Request{
    private static String myAction = "get-stock-provider-info-data";
    private String stockIds ;
    private String urlFormat = "&stockIds=%s";

    public StockProviderInfoRequest() {
        this(myAction);
    }
    private StockProviderInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() {

        //要检验！！！！！！！！！！！！！！！！！！！！！！
        if(stockIds == null ){
            System.out.println("StockWarehouseInfoRequest必须需要stockIds参数");
            System.out.println("StockWarehouseInfoRequest必须需要stockIds参数");
            System.out.println("StockWarehouseInfoRequest必须需要stockIds参数");
            return null;
        }else{
            return super.getPublicUrl() + String.format(urlFormat, stockIds);
        }
    }
}
