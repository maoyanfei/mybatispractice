package mapper;

import po.Orders;
import po.OrdersUser;
import po.User;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public interface OrdersMapperCustom {

    public List<OrdersUser> findOrdersUserResultType();

    public List<Orders> findOrdersUserResultMap();

    public List<Orders> findOrdersAndOrderdetailResultMap();

    public List<User> findUserAndItemsResultMap();

    public List<Orders> findOrdersUserLazyLoading();

}
