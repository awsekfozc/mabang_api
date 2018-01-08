package com.smartdo.scc.mabang.backend.request;

import lombok.Data;

@Data
public class StockMachiningInfoRequest extends Request {
    private static String myAction = "get-stock-machining-info-data"; //必填成熟
    private String stockIds ; //必填参数
    private String urlFormat = "&stockIds=%s";

    public StockMachiningInfoRequest() {
        this(myAction);
    }
    private StockMachiningInfoRequest(String action) {
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
