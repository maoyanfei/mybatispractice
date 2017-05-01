package dao;

import po.Customer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import po.User;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class UserDaoImp1 implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImp1(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public UserDaoImp1() {
    }

    public User findUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("dao.UserDao.findUserById", id);
        sqlSession.close();
        return user;
    }

    public void insertUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("insertUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("User.deleteUserById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateUser(User user) {

    }

    public List<User> findUserByUsername(String username) {
        return null;
    }

    public List<User> findUserByCustomer(Customer customer) {
        return null;
    }
}
