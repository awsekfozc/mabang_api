import com.smartdo.scc.mabang.backend.bean.User;
import com.smartdo.scc.mabang.backend.dao.IUser;
import com.smartdo.scc.mabang.backend.factory.DbFactory;
import org.apache.ibatis.session.SqlSession;

public class Main {

    public static void main(String[] args) {
        SqlSession session = DbFactory.getInstance().openSession();
        session.getConfiguration().addMapper(IUser.class);
        try {
            IUser iuser = session.getMapper(IUser.class);
            User user = iuser.getUserByID(1);
            System.out.println("名字："+user.getName());
            System.out.println("所属部门："+user.getDept());
            System.out.println("主页："+user.getWebsite());
        } finally {
            session.close();
        }
    }
}