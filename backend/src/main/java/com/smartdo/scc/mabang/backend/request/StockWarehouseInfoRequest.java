package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

@Data
public class StockWarehouseInfoRequest extends Request{
    private static String myAction = "get-stock-warehouse-info-data";
    private String stockIds ;
    private String urlFormat = "&stockIds=%s";
//    private String[] stockIds ;

    private StockWarehouseInfoRequest(String action) {
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

    public StockWarehouseInfoRequest() {
        this(myAction);
    }
}
