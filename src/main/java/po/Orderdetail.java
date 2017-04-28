package po;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/28.
 */
public class Orderdetail {

    private Integer id;

    private Integer ordersId;

    private Integer itemsId;

    private Integer itemsNum;

    //明细对应的商品信息
    private Items items;

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
     * Getter for property 'ordersId'.
     *
     * @return Value for property 'ordersId'.
     */
    public Integer getOrdersId() {
        return ordersId;
    }

    /**
     * Setter for property 'ordersId'.
     *
     * @param ordersId Value to set for property 'ordersId'.
     */
    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * Getter for property 'itemsId'.
     *
     * @return Value for property 'itemsId'.
     */
    public Integer getItemsId() {
        return itemsId;
    }

    /**
     * Setter for property 'itemsId'.
     *
     * @param itemsId Value to set for property 'itemsId'.
     */
    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    /**
     * Getter for property 'itemsNum'.
     *
     * @return Value for property 'itemsNum'.
     */
    public Integer getItemsNum() {
        return itemsNum;
    }

    /**
     * Setter for property 'itemsNum'.
     *
     * @param itemsNum Value to set for property 'itemsNum'.
     */
    public void setItemsNum(Integer itemsNum) {
        this.itemsNum = itemsNum;
    }

    /**
     * Getter for property 'items'.
     *
     * @return Value for property 'items'.
     */
    public Items getItems() {
        return items;
    }

    /**
     * Setter for property 'items'.
     *
     * @param items Value to set for property 'items'.
     */
    public void setItems(Items items) {
        this.items = items;
    }
}
