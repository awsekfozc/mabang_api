package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StockStorageLogRequest extends Request {
    private static String stockStorageLogAction = "get-stock-storage-log-data";// Y get-stock-storage-log-data
    private String stockIds;// N 库存sku 编号，多个已逗号隔开；最多支持10 个
    private Integer page;// N 当前页数：每页默认显示500 条
    private Date updateTimeStart;//N 	最后更新开始时间
    private Date updateTimeEnd;//N 	最后更新结束时间

    private StockStorageLogRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() throws IncorrectParametersError {
        Map map = new HashMap();
        map.put("stockIds", stockIds);
        map.put("page", page);

        if(updateTimeStart !=null && updateTimeEnd !=null) {
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String UpdateTimeStartStr = "";
            String UpdateTimeEndStr = "";
            try {
                UpdateTimeStartStr = java.net.URLEncoder.encode(sdf.format(updateTimeStart), "utf-8");
                UpdateTimeEndStr = java.net.URLEncoder.encode(sdf.format(updateTimeEnd), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            map.put("updateTimeStart", UpdateTimeStartStr);
            map.put("updateTimeEnd", UpdateTimeEndStr);
        }

        String Parameters = SplicingParameters(map);
        return super.getPublicUrl() + Parameters;
    }

    public StockStorageLogRequest() {
        this(stockStorageLogAction);
    }
}
