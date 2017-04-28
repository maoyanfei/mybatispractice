package dao;

import dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/25.
 */
public class UserDaoImpTestTest {
    @Test
    public void testtypeAliases() throws Exception {

        String resource = "Configuration.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        userDao.findUserByUsername("y");

    }

}