import bean.User;
import dao.UserDao;
import dao.UserDaoImp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class UserDaoImpTest {

    private SqlSessionFactory sqlSessionFactory;

    public void setUp() throws IOException {
        String resource = "Configuration.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void testFindUserById() throws IOException {
        if (sqlSessionFactory == null) {
            setUp();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findUserById(4);
        System.out.println(user);
    }

    public void testFindUserByUsername() throws IOException {
        if (sqlSessionFactory == null) {
            setUp();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findUserByUsername("y");
        System.out.println(userList.get(0).getUsername());
    }

    public void testInsertUser() throws IOException {
        if (sqlSessionFactory == null) {
            setUp();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("朱小明");
        user.setPassword("朱大明");
        userDao.insertUser(user);
        sqlSession.commit();
    }

    public void testtypeAliases() {

    }


}
