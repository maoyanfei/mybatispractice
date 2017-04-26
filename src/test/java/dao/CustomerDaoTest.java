package dao;

import bean.Customer;
import bean.User;
import db.Conn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/25.
 */
public class CustomerDaoTest {
    @Test
    public void findCustomerByUser() throws Exception {

        SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);

        Customer customer = new Customer();
        User user = new User();
        user.setUsername("李亚男");
        user.setPassword("liyanan");

        customer.setUser(user);
        Customer customerVariable = customerDao.findCustomerByCustomer(customer);

    }

    @Test
    public void findCustomerById() throws Exception {

        SqlSessionFactory sqlSessionFactory = Conn.getSqlSessionFactory();
        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);

        Customer customer = customerDao.findCustomerById(1);

        System.out.println(customer);
    }

}