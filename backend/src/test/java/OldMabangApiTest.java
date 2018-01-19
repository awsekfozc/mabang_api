import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.*;
import com.smartdo.scc.mabang.backend.request.*;
import org.junit.Test;

public class OldMabangApiTest {

    @Test
    public void testStockInfoApi() {
        StockInfoRequst request = new StockInfoRequst();
        MabangAPI.create(request)
                .setPipeline(new StockInfoPipeline())
                .start();
    }

    @Test
    public void testStockWarehouseInfoApi() {
        StockWarehouseInfoRequest request = new StockWarehouseInfoRequest(); // 2 验证
//        request.setStockIds("1338956,1338991");
        request.setStockIds("1339076,1339076");
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
        StockProviderInfoRequest request = new StockProviderInfoRequest(); // 4
        MabangAPI.create(request)
                .setPipeline(new StockProviderInfoPipeline())
                .start();

    }

    @Test
    public void testOrderInfoApi() {
        OrderInfoRequest request = new OrderInfoRequest(); //5 验证
        request.setPage(1);
        request.setTableBase(1);

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
        request.setPurchaseGroups("1100000191");
        MabangAPI.create(request)
                .setPipeline(new ProductPurchaseStorageInInfoPipeline())
                .start();
    }

    @Test
    public void testStockStorageLogApi() {
        StockStorageLogRequest request = new StockStorageLogRequest(); //9
        MabangAPI.create(request)
                .setPipeline(new StockStorageLogPipeline())
                .start();
    }

}



