package po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class Orders implements Serializable{//用到缓存的查询结果映射的pojo类需要实现Serializable借口

    private Integer id;

    private Integer userId;

    private String number;

    private Date createtime;

    private String note;

    //用户信息
    //Orders表若关联了其他表，那么需要在Orders中为该表配置实体类属性。同时在
    private User user; // 后添加的属性，因为该类本属于父类可任意扩展自己属性。不受约束

    //订单明细
    private List<Orderdetail> orderdetails;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Getter for property 'number'.
     *
     * @return Value for property 'number'.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter for property 'number'.
     *
     * @param number Value to set for property 'number'.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Getter for property 'createtime'.
     *
     * @return Value for property 'createtime'.
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Setter for property 'createtime'.
     *
     * @param createtime Value to set for property 'createtime'.
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * Getter for property 'note'.
     *
     * @return Value for property 'note'.
     */
    public String getNote() {
        return note;
    }

    /**
     * Setter for property 'note'.
     *
     * @param note Value to set for property 'note'.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Getter for property 'user'.
     *
     * @return Value for property 'user'.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for property 'user'.
     *
     * @param user Value to set for property 'user'.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for property 'orderdetails'.
     *
     * @return Value for property 'orderdetails'.
     */
    public List<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    /**
     * Setter for property 'orderdetails'.
     *
     * @param orderdetails Value to set for property 'orderdetails'.
     */
    public void setOrderdetails(List<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
