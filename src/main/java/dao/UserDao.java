package dao;

import bean.User;
import db.Conn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    public void getSqlSessionFactory() {
        SqlSession sqlSession = null;
        try {
//            logger.debug("UserDao的getSqlSessionFactory");
            SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            User user = sqlSession.selectOne("User.find", 1);
            System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getPassword());
        } catch (IOException e) {
            logger.error("UserDao的getSqlSessionFactory出错");
        } finally {
            sqlSession.close();
        }
    }
}
