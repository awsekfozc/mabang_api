package com.smartdo.scc.mabang.backend.request;

import com.smartdo.scc.mabang.backend.exceptions.IncorrectParametersError;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductPurchaseStorageInInfoRequest extends Request {
    private static String productPurchaseStorageInInfoAction = "get-product-purchase-storage-in-info-data";
    private String purchaseGroups ;//Y 	采购单号，多个已逗号隔开；最多支持10个

    public ProductPurchaseStorageInInfoRequest() {
        this(productPurchaseStorageInInfoAction);
    }
    private ProductPurchaseStorageInInfoRequest(String action) {
        super(action);
    }

    @Override
    public String stitchingRequest() throws IncorrectParametersError{
        if(purchaseGroups == null ){
            throw new IncorrectParametersError("ProductPurchaseStorageInInfoRequest必须设置[purchaseGroups]参数");
        }else{
            boolean flag = checkLength(purchaseGroups);
            if(!flag){
                throw new IncorrectParametersError("StockWarehouseInfoRequest的[stockIds]参数多个编号以逗号隔开；最对支持 10 个");
            }
            boolean flagEmpty = checkIsEmpty(purchaseGroups);
            if(flagEmpty){
                throw new IncorrectParametersError("ProductPurchaseStorageInInfoRequest必须设置[purchaseGroups]参数");
            }
            Map map = new HashMap();
            map.put("purchaseGroups",purchaseGroups);
            String Parameters = SplicingParameters(map);
            System.out.println(Parameters);
            System.out.println(super.getPublicUrl() + Parameters);
            return super.getPublicUrl() + Parameters;
        }
    }

}
