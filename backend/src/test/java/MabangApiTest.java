import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.*;
import com.smartdo.scc.mabang.backend.request.*;
import org.junit.Test;

public class MabangApiTest {

    @Test
    public void testProductApi() {
        ProductRequst request = new ProductRequst(); // 1 验证
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }

    @Test
    public void testStockWarehouseInfoApi() {
        StockWarehouseInfoRequest request = new StockWarehouseInfoRequest(); // 2 验证
//        request.setStockIds("1338956,1338991");
        request.setStockIds("1");
        MabangAPI.create(request)
                .setPipeline(new StockWarehouseInfoPipeline())
                .start();
    }

    @Test
    public void testStockMachiningInfoApi() {
        StockMachiningInfoRequest request = new StockMachiningInfoRequest(); // 3 验证
        request.setStockIds("1338956,1338991"); //有加工信息
        MabangAPI.create(request)
                .setPipeline(new StockMachiningInfoPipeline())
                .start();
    }

    @Test
    public void testStockProviderInfoApi() {
        StockProviderInfoRequest request = new StockProviderInfoRequest(); // 4 没有测试！
        request.setStockIds("1338956,1338991");
        request.setStockIds("276472,276472");
        MabangAPI.create(request)
                .setPipeline(new StockProviderInfoPipeline())
                .start();
    }

    @Test
    public void testOrderInfoApi() {
        OrderInfoRequest request = new OrderInfoRequest(); //5 验证
        request.setPage(1);
        request.setTableBase(1);
//        request.setPage(1);
//        request.setTableBase(3);

        MabangAPI.create(request)
                .setPipeline(new OrderInfoPipeline())
                .start();
    }

    @Test
    public void testFbaInfoApi() {
        FbaInfoRequst request = new FbaInfoRequst();   // 6 验证
        request.setPage(1);

        MabangAPI.create(request)
                .setPipeline(new FbaInfoPipeline())
                .start();
    }

    @Test
    public void testProductPurchaseInfoApi() {
        ProductPurchaseInfoRequest productPurchaseInfoRequest = new ProductPurchaseInfoRequest(); //7
        productPurchaseInfoRequest.setPage(1);
        MabangAPI.create(productPurchaseInfoRequest)
                .setPipeline(new ProductPurchaseInfoPipeline())
                .start();
    }

    @Test
    public void testProductPurchaseStorageInInfoApi() {
        ProductPurchaseStorageInInfoRequest request = new ProductPurchaseStorageInInfoRequest(); //8
        request.setPurchaseGroups("1010000015");
        MabangAPI.create(request)
                .setPipeline(new ProductPurchaseStorageInInfoPipeline())
                .start();
    }

}



