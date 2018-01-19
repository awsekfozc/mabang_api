package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StockWarehouseInfoRequest extends Request{
    private static String stockWarehouseInfoAction = "get-stock-warehouse-info-data";//Y
    private String stockIds ;  //Y   3.0 库存 sku 编号多个已逗号隔开；最对支持 10 个
    private Date updateTimeStart;  //无需传递 ，仅为后续生成response中使用
    private Date updateTimeEnd;  //无需传递 ，仅为后续生成response中使用

    private StockWarehouseInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest()throws IncorrectParametersError {
        if(stockIds == null){
            throw new IncorrectParametersError("StockWarehouseInfoRequest必须设置[stockIds]参数");
        }else{
            boolean flag = checkLength(stockIds);
            if(!flag){
                throw new IncorrectParametersError("StockWarehouseInfoRequest的[stockIds]多个参数编号以逗号隔开；最对支持 10 个");
            }
            boolean flagEmpty = checkIsEmpty(stockIds);
            if(flagEmpty){
                throw new IncorrectParametersError("StockWarehouseInfoRequest必须设置[stockIds]参数");
            }
            Map map = new HashMap();
            map.put("stockIds",stockIds);
            String Parameters = SplicingParameters(map);
//            System.out.println(Parameters);
            System.out.println(super.getPublicUrl() + Parameters);
            return super.getPublicUrl() + Parameters;
        }
    }

    public StockWarehouseInfoRequest() {
        this(stockWarehouseInfoAction);
    }

}
