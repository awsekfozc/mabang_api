import com.smartdo.scc.mabang.backend.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;


/**
 *
 */
public class HelloWord {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            User user = (User) session.selectOne(
//                    "com.smartdo.scc.mabang.backend.UserMapper.GetUserByID", 1);
//            if(user!=null){
//                String userInfo = "名字："+user.getName()+", 所属部门："+user.getDept()+", 主页："+user.getWebsite();
//                System.out.println(userInfo);
//            }
//        } finally {
//            session.close();
//        }

        try {
            User user = new User(22,"zhang","TTT","www.baidu.com","123456");
            session.selectOne(
                    "com.smartdo.scc.mabang.backend.UserMapper.add", user);
        } finally {
            session.close();
        }

    }

}