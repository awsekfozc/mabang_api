import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.OrderInfoPipeline;
import com.smartdo.scc.mabang.backend.request.OrderInfoRequest;
import org.junit.Test;

public class MabangApiTest {

    @Test
    public void testProductApi() {

//        ProductRequst request = new ProductRequst(); // 1 验证

//        StockWarehouseInfoRequest request = new StockWarehouseInfoRequest(); // 2 验证
//        request.setStockIds("1338956,1338991");

//        StockMachiningInfoRequest request = new StockMachiningInfoRequest(); // 3 验证
//        request.setStockIds("1338956,1338991"); //有加工信息

//        StockProviderInfoRequest request = new StockProviderInfoRequest(); // 4 没有测试！
//        request.setStockIds("1338956,1338991");
//        request.setStockIds("276472,276472");

//        FbaInfoRequst request = new FbaInfoRequst();   // 6 验证
//        request.setPage(1);

//        ProductPurchaseStorageInInfoRequest request = new ProductPurchaseStorageInInfoRequest(); //8
//        request.setPurchaseGroups("1010000015");

        OrderInfoRequest request = new OrderInfoRequest(); //5 验证
        request.setPage(1);
        request.setTableBase(1);

        MabangAPI.create(request)
                .setPipeline(new OrderInfoPipeline())
                .start();
    }
   
}



