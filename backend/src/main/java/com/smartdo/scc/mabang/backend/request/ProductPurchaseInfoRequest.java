package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.7 获取企业采购商品信息Request
 */
@Data
@Slf4j
public class ProductPurchaseInfoRequest extends Request {
    //Y:必填  N：非必填
    private static String productPurchaseInfoAction = "get-product-purchase-info-data";//Y 	get-product-purchase-info-data
    private Integer page; //Y 	当前页数：每页默认显示100条
    private Date updateTimeStart;  //N 	最后更新开始时间
    private Date updateTimeEnd;  ///N 	最后更新结束时间


    private ProductPurchaseInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() throws IncorrectParametersError{
        if (page == null){
            throw new IncorrectParametersError("ProductPurchaseInfoRequest必须设置[page]参数");
        }else{
            Map map = new HashMap();
            map.put("page",page);

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
            log.info(super.getPublicUrl() + Parameters);
            return super.getPublicUrl() + Parameters;
        }
    }

    public ProductPurchaseInfoRequest() {
        this(productPurchaseInfoAction);
    }
}
