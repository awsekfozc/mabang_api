import com.smartdo.scc.mabang.backend.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.ProductPipeline;
import com.smartdo.scc.mabang.backend.request.ProductRequst;
import org.junit.Test;

public class MabangApiTest {


    @Test
    public void testProductApi() {
        ProductRequst request = new ProductRequst();
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }
}
