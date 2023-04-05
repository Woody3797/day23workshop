package ibf2022.paf.day23workshop.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    
    private int id;
    private DateTime orderDate;
    private Integer customer_id;
    private Double totalDiscountedPrice;
    private Double costPrice;

    public OrderDetails() {
    }

    public OrderDetails(int id, DateTime orderDate, Integer customer_id, Double totalDiscountedPrice,
            Double costPrice) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer_id = customer_id;
        this.totalDiscountedPrice = totalDiscountedPrice;
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
    public Double getTotalDiscountedPrice() {
        return totalDiscountedPrice;
    }
    public void setTotalDiscountedPrice(Double totalDiscountedPrice) {
        this.totalDiscountedPrice = totalDiscountedPrice;
    }
    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails [id=" + id + ", orderDate=" + orderDate + ", customer_id=" + customer_id
                + ", totalDiscountedPrice=" + totalDiscountedPrice + ", costPrice=" + costPrice + "]";
    }

    public static OrderDetails create(SqlRowSet rs) {
        OrderDetails orderDetails = new OrderDetails();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime orderDate = dtf.parseDateTime(rs.getString("order_date"));
        
        orderDetails.setId(rs.getInt("order_id"));
        orderDetails.setOrderDate(orderDate);
        orderDetails.setCustomer_id(rs.getInt("customer_id"));
        orderDetails.setTotalDiscountedPrice(rs.getDouble("discounted_price"));
        orderDetails.setCostPrice(rs.getDouble("cost_price"));
        return orderDetails;
    }

    public JsonObject toJson() {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
        String orderDate = getOrderDate().toString(dtf);

        return Json.createObjectBuilder()
        .add("order_id", getId())
        .add("order_date", orderDate)
        .add("customer_id", getCustomer_id())
        .add("discounted_price", getTotalDiscountedPrice())
        .add("cost_price", getCostPrice())
        .build();
    }

    
    
}
