package po;

import java.util.Date;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class User {

    private int id;
    private String username;            // 用户姓名
    private String sex;                 // 性别
    private Date birthday;              // 生日
    private String address;             // 地址

    //用户创建的订单列表
    private List<Orders> ordersList;

    //getter and setter

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for property 'username'.
     *
     * @return Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for property 'username'.
     *
     * @param username Value to set for property 'username'.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for property 'sex'.
     *
     * @return Value for property 'sex'.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter for property 'sex'.
     *
     * @param sex Value to set for property 'sex'.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter for property 'birthday'.
     *
     * @return Value for property 'birthday'.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter for property 'birthday'.
     *
     * @param birthday Value to set for property 'birthday'.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter for property 'address'.
     *
     * @return Value for property 'address'.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for property 'address'.
     *
     * @param address Value to set for property 'address'.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setter for property 'ordersList'.
     *
     * @param ordersList Value to set for property 'ordersList'.
     */
    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
