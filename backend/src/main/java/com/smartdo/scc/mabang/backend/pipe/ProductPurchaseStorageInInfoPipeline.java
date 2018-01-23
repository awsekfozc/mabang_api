package com.smartdo.scc.mabang.backend.pipe;

import com.smartdo.scc.mabang.backend.bean.ProductPurchaseStorageInInfo;
import com.smartdo.scc.mabang.backend.dao.IProductPurchaseStorageInInfoDao;
import com.smartdo.scc.mabang.backend.exceptions.ResponseTypeError;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import com.smartdo.scc.mabang.backend.response.ProductPurchaseStorageInInfoResponse;
import com.smartdo.scc.mabang.backend.response.Response;
import org.apache.ibatis.session.SqlSession;

public class ProductPurchaseStorageInInfoPipeline implements Pipeline {
    @Override
    public void save(Response response) throws ResponseTypeError {
        if (!(response instanceof ProductPurchaseStorageInInfoResponse))
            throw new ResponseTypeError("不是商品响应类型");
        ProductPurchaseStorageInInfoResponse productPurchaseStorageInInfoResponse = (ProductPurchaseStorageInInfoResponse) response;
        //持久化
        SqlSession session = DbFactory.getInstance().openSession();
        IProductPurchaseStorageInInfoDao dao = DbFactory.getBeanMapper(IProductPurchaseStorageInInfoDao.class, session);
        try {
            for (ProductPurchaseStorageInInfo productPurchaseStorageInInfo : productPurchaseStorageInInfoResponse.getEntityList()) {
                if (dao.IsExist(productPurchaseStorageInInfo) > 0) {
                    dao.update(productPurchaseStorageInInfo);
                } else {
                    dao.add(productPurchaseStorageInInfo);
                }
            }
        } finally {
            session.commit();
            session.close();
        }
    }
}
