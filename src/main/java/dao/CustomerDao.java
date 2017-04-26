package dao;

import bean.Customer;
import bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public interface CustomerDao {

    public Customer findCustomerById(int id);

    public Customer findCustomerByCustomer(@Param(value = "customer") Customer customer);//指定传入到xml配置文件中的参数的使用名称

    public Customer findCustomerById(List<Integer> idList);
}
