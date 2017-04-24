package dao;

import bean.User;
import db.Conn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class UserMapper {

    private static final Logger logger = Logger.getLogger(UserMapper.class);

    public void findUserById() {
        SqlSession sqlSession = null;
        try {
//            logger.debug("UserMapper的getSqlSessionFactory");
            SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            User user = sqlSession.selectOne("User.findUserById", 1);
            System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getPassword());
        } catch (IOException e) {
            logger.error("UserMapper的getSqlSessionFactory出错");
        } finally {
            sqlSession.close();
        }
    }

    public void findUserByUsername() {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            List<User> userList = sqlSession.selectList("User.findUserByUsername", "yong");
            System.out.println(userList.get(0).getUsername());
        } catch (IOException e) {
            logger.error("UserMapper的findUserByUsername");
        } finally {
            sqlSession.close();
        }
    }

    public void insertUser() {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setUsername("小明");
            user.setPassword("xiaoming");
            int count = sqlSession.insert("User.insertUser", user);
            sqlSession.commit();//对数据库有修改的操作需要提交事务
            System.out.println(count);
        } catch (IOException e) {
            logger.error("UserMapper的insertUser");
        } finally {
            sqlSession.close();
        }
    }
}
