package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.6 获取企业FBA商品信息
 */
@Data
@Slf4j
public class FbaInfoRequst extends Request {

    private static String fbaInfoAction = "get-fba-info-data"; //必填参数
    private Integer page; //必填参数
    private Date updateTimeStart;
    private Date updateTimeEnd;
    private String urlFormat = "&page=%d";
//    private String urlFormat = "&page=%d&updateTimeStart=%s&updateTimeEnd=%s";

    private FbaInfoRequst(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() throws IncorrectParametersError {
        if (page == null) {
            throw new IncorrectParametersError("FbaInfoRequst必须设置[page]参数");
        }else{
            Map map = new HashMap();
            map.put("page",page);
            map.put("updateTimeStart",updateTimeStart);
            map.put("updateTimeEnd",updateTimeEnd);
            String Parameters = SplicingParameters(map);
            System.out.println(Parameters);
            System.out.println(super.getPublicUrl() + Parameters);
            return super.getPublicUrl() + Parameters;
        }
    }

    public FbaInfoRequst() {
        this(fbaInfoAction);
    }

}
