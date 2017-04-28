package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import po.OrdersCustom;

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
    public void setUp() throws IOException {
        String resource = "Configuration.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();

        System.out.println(list);

        sqlSession.close();
    }

}