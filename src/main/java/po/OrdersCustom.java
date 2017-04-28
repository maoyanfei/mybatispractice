package po;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class OrdersCustom extends Customer{//容纳查询出的User内容
    //添加用户属性
    /*USER.username,
	  USER.sex,
	  USER.address */

    private String username;
    private String sex;
    private String address;

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
}
