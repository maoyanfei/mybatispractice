package mapper;

import dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import po.Orders;
import po.OrdersUser;
import po.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class OrdersMapperCustomTest {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void setUp() throws IOException {//Test方法执行前的  负责环境加载的方法
        String resource = "Configuration.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findOrdersUserResultMap() throws Exception {
        sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> ordersList = ordersMapperCustom.findOrdersUserResultMap();

        System.out.println(ordersList);
    }


    @Test
    public void findOrdersUser() throws Exception {
        sqlSession = sqlSessionFactory.openSession();

        //带着该mapper接口的全路径去mapper映射文件的列表中去对应查询该接口对应的映射文件
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<OrdersUser> ordersUserList = ordersMapperCustom.findOrdersUserResultType();

        System.out.println(ordersUserList);
    }

    @Test
    public void findOrdersAndOrderdetailResultMap() throws Exception {
        sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> ordersList = ordersMapperCustom.findOrdersAndOrderdetailResultMap();

        System.out.println(ordersList);
    }

    @Test
    public void findUserAndItemsResultMap() throws Exception {
        sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<User> userList = ordersMapperCustom.findUserAndItemsResultMap();

        System.out.println(userList);
    }

    @Test
    public void findOrdersUserLazyLoading() throws Exception {

        sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> ordersList = ordersMapperCustom.findOrdersUserLazyLoading();//Debug

        for (Orders orders : ordersList) {
            //延迟加载被触发的关键在此，当orders对象中的user属性的方法被调用的时候开始对其进行延迟加载
            //想要知道如何触发，探索源码
            User user = orders.getUser();
            System.out.println(user);
        }
    }

    //一级缓存测试
    @Test
    public void testCache1() throws Exception {
        sqlSession = sqlSessionFactory.openSession();
        //创建UserDao对象,mybatis自动生成代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //查询使用的是同一个session
        //第一次发起请求，查询Id 为1的用户信息
        User user1 = userDao.findUserById(1);
        System.out.println(user1);

        /*
            //如果sqlSession去执行commit操作（执行插入、更新、删除），
            // 清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
            //更新user1的信息，
            user1.setUsername("李飞");
            //user1.setSex("男");
            //user1.setAddress("北京");
            userDao.updateUser(user1);
            System.out.println("updateUser /================================/ had down");
            //提交事务,才会去清空缓存
            sqlSession.commit();
        */

        //第二次发起请求，查询Id 为1的用户信息
        //通过log结果可以看出两次查询之间sqlsession不含commit操作。第二次没有发出sql查询请求，
        User user2 = userDao.findUserById(1);
        System.out.println(user2);
    }

    //二级缓存测试
    @Test
    public void testCache2() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        //创建UserDao对象,mybatis自动生成代理对象
        UserDao userDao = sqlSession1.getMapper(UserDao.class);
        //sqlSession1 执行查询 写入缓存(第一次查询请求)
        User user1 = userDao.findUserById(1);
        System.out.println(user1);
        sqlSession1.close();

        /*
        //sqlSession3  执行提交  清空缓存
        UserDao userDao3 = sqlSession3.getMapper(UserDao.class);
        User user3 = userDao3.findUserById(1);
        user3.setSex("女");
        user3.setAddress("山东济南");
        user3.setUsername("崔建");
        userDao3.updateUser(user3);
        //提交事务，清空缓存
        sqlSession3.commit();//事物的提交也会将mapper下的二级缓存清空
        sqlSession3.close();
        */

        //sqlSession2 执行查询(第二次查询请求)
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = userDao2.findUserById(1);
        System.out.println(user2);
        sqlSession2.close();
    }

    @After
    public void shutOff() {
        sqlSession.close();
    }

}