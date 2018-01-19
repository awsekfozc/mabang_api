package com.smartdo.scc.mabang.backend.factory;

import com.smartdo.scc.mabang.backend.exceptions.HttpClientError;
import com.smartdo.scc.mabang.backend.request.*;
import com.smartdo.scc.mabang.backend.response.*;

/**
 * 响应生成工厂
 */
public class ResponseFactory {

    public static Response getResponse(Request request) throws HttpClientError {
        if (request instanceof StockInfoRequst) {
            return new StockInfoResponse(request.getResult());
        }
        if (request instanceof StockWarehouseInfoRequest) {
            return new StockWarehouseInfoResponse(request.getResult(),request);
        }
        if (request instanceof StockMachiningInfoRequest) {
            return new StockMachiningInfoResponse(request.getResult(),((StockMachiningInfoRequest) request).getStockIds(),request);
        }
        if (request instanceof StockProviderInfoRequest) {
            return new StockProviderInfoResponse(request.getResult(),((StockProviderInfoRequest) request).getStockIds());
        }
        if (request instanceof FbaInfoRequst) {
            return new FbaInfoResponse(request.getResult());
        }
        if (request instanceof ProductPurchaseStorageInInfoRequest) {
            return new ProductPurchaseStorageInInfoResponse(request.getResult(),((ProductPurchaseStorageInInfoRequest) request).getPurchaseGroups(),request);
        }
        if (request instanceof OrderInfoRequest) {
            return new OrderInfoResponse(request.getResult());
        }
        if (request instanceof ProductPurchaseInfoRequest) {
            return new ProductPurchaseInfoResponse(request.getResult());
        }
        if (request instanceof StockStorageLogRequest) {
            return new StockStorageLogResponse(request.getResult());
        }
        return null;
    }
}
