package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

    @Before
    public void setUp() throws IOException {//Test方法执行前的  负责环境加载的方法
        String resource = "Configuration.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> ordersList = ordersMapperCustom.findOrdersUserResultMap();

        System.out.println(ordersList);

        sqlSession.close();
    }


    @Test
    public void findOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //带着该mapper接口的全路径去mapper映射文件的列表中去对应查询该接口对应的映射文件
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<OrdersUser> ordersUserList = ordersMapperCustom.findOrdersUserResultType();

        System.out.println(ordersUserList);

        sqlSession.close();
    }

    @Test
    public void findOrdersAndOrderdetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> ordersList = ordersMapperCustom.findOrdersAndOrderdetailResultMap();

        System.out.println(ordersList);

        sqlSession.close();
    }

    @Test
    public void findUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<User> userList = ordersMapperCustom.findUserAndItemsResultMap();

        System.out.println(userList);

        sqlSession.close();
    }


}