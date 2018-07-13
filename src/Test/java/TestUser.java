import com.starlight.rfid.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class TestUser {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    static {
        try {
            reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<User> userList = sqlSession.selectList("com.starlight.rfid.dao.IUserDao.queryUserByName", "%宋俊男%");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i).toString());
            }
            User user = new User();
            user.setId(100);
            user.setPassword("sjn");
            user.setBirthday(new Date());
            user.setUsername("宋俊男");
            user.setSex("1");
            user.setAddress("山东烟台");
            int isAdd = sqlSession.insert("com.starlight.rfid.dao.IUserDao.addUser", user);
            sqlSession.commit();//不要忘记
        } finally {
            sqlSession.close();
        }
    }
}
