package ibf2022.paf.day23workshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day23workshop.model.OrderDetails;

@Repository
public class OrderDetailsRepository {
    
    @Autowired
    JdbcTemplate template;

    public OrderDetails getOrderDetailsWithDiscount(Integer orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(DBQueries.ORDER_DETAILS_WITH_DISCOUNT_QUERY, orderId);

        while (rs.next()) {
            orderDetailsList.add(OrderDetails.create(rs));
        }
        return orderDetailsList.get(0);
    }
}
