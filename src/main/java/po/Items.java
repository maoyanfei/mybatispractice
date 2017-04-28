package po;

import java.util.Date;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class Items {

    private Integer id;

    private String name;

    private Float price;

    private String pic;

    private Date createtime;

    private String detail;

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
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'price'.
     *
     * @return Value for property 'price'.
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Setter for property 'price'.
     *
     * @param price Value to set for property 'price'.
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Getter for property 'pic'.
     *
     * @return Value for property 'pic'.
     */
    public String getPic() {
        return pic;
    }

    /**
     * Setter for property 'pic'.
     *
     * @param pic Value to set for property 'pic'.
     */
    public void setPic(String pic) {
        this.pic = pic;
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
     * Getter for property 'detail'.
     *
     * @return Value for property 'detail'.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Setter for property 'detail'.
     *
     * @param detail Value to set for property 'detail'.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
