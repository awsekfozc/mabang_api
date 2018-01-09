package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseInfo;
import com.smartdo.scc.mabang.backend.bean.PurchaseDetail;
import com.smartdo.scc.mabang.backend.dao.IProductPurchaseInfoDao;
import com.smartdo.scc.mabang.backend.dao.IPurchaseDetailDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.ProductPurchaseInfoResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import org.apache.ibatis.session.SqlSession;

public class ProductPurchaseInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof ProductPurchaseInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        ProductPurchaseInfoResponse productPurchaseInfoResponse = (ProductPurchaseInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IProductPurchaseInfoDao iProductPurchaseInfoDao = DbFactory.getBeanMapper(IProductPurchaseInfoDao.class, session);
        IPurchaseDetailDao iPurchaseDetailDao = DbFactory.getBeanMapper(IPurchaseDetailDao.class, session);
        try {
            for (ProductPurchaseInfo productPurchaseInfo : productPurchaseInfoResponse.getProductPurchaseInfoList()) {
                iProductPurchaseInfoDao.add(productPurchaseInfo);
            }
            for (PurchaseDetail purchaseDetail : productPurchaseInfoResponse.getPurchaseDetailList()) {
                iPurchaseDetailDao.add(purchaseDetail);
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
