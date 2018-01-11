package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StockProviderInfoRequest extends Request{
    private static String stockProviderInfoAction = "get-stock-provider-info-data";//Y
    private String stockIds ;//N 	3.0 库存 sku 编号多个已逗号隔开；最对支持 10 个
    private Integer page; //N 	页数 默认为 1 页每页展示 100 条
    private Date updateTimeStart;//N 	最后更新开始时间
    private Date updateTimeEnd;//N 	最后更新结束时间

    public StockProviderInfoRequest() {
        this(stockProviderInfoAction);
    }
    private StockProviderInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest()throws IncorrectParametersError {
        if(stockIds != null){
            boolean flag = checkLength(stockIds);
            if(!flag){
                throw new IncorrectParametersError("StockProviderInfoRequest[stockIds]多个参数编号以逗号隔开；最对支持 10 个");
            }
            boolean flagEmpty = checkIsEmpty(stockIds);
            if(flagEmpty){
                throw new IncorrectParametersError("StockWarehouseInfoRequest必须设置[stockIds]参数");
            }
        }
        Map map = new HashMap();
        map.put("stockIds",stockIds);
        String Parameters = SplicingParameters(map);
        System.out.println(Parameters);
        System.out.println(super.getPublicUrl() + Parameters);
        return super.getPublicUrl() + Parameters;
    }
}
