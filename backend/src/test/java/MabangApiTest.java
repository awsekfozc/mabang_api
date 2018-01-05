import com.smartdo.scc.mabang.backend.api.MabangAPI;
import com.smartdo.scc.mabang.backend.pipe.ProductPipeline;
import com.smartdo.scc.mabang.backend.request.ProductRequst;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.neo.mapper")
public class MabangApiTest {


    @Test
    public void testProductApi() {
        ProductRequst request = new ProductRequst();
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }

    public static void main(String[] args) {
        ProductRequst request = new ProductRequst();
        MabangAPI.create(request)
                .setPipeline(new ProductPipeline())
                .start();
    }
}



