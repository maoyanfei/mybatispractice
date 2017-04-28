package dao;

import db.Conn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import po.User;

import java.io.IOException;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class UserDaoImp2 {

    private static final Logger logger = Logger.getLogger(UserDaoImp2.class);

    private static SqlSessionFactory sqlSessionFactory = null;


    static {
        try {
            sqlSessionFactory = Conn.getSqlSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findUserById() {
        logger.debug("UserMapper的findUserById方法===========================");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("User.findUserById", 1);
//        System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getPassword());
        sqlSession.close();
    }

    /**
     * 模糊查询
     */
    public void findUserByUsername() {
        logger.debug("UserMapper的findUserByUsername方法===========================");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("User.findUserByUsername", "yong");
        System.out.println(userList.get(0).getUsername());
        sqlSession.close();
    }

    public void insertUser() {
        logger.debug("UserMapper的insertUser方法===========================");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("小明");
//        user.setPassword("xiaoming");
        int count = sqlSession.insert("User.insertUser", user);
        sqlSession.commit();//对数据库有修改的操作需要提交事务
        System.out.println(count);//1
        sqlSession.close();
    }

    public void deleteUserById() {
        logger.debug("UserMapper的deleteUserById方法===========================");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int result = sqlSession.delete("User.deleteUserById", 1);
        sqlSession.commit();
        System.out.println(result);//1
        sqlSession.close();
    }

    public void updateUserById() {
        logger.debug("UserMapper的updateUserById方法===========================");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(4);
        user.setUsername("jyg");
//        user.setPassword("jiyongguang");
        int result = sqlSession.delete("User.updateUserById", user);
        sqlSession.commit();
        System.out.println(result);//1
        sqlSession.close();
    }
}
