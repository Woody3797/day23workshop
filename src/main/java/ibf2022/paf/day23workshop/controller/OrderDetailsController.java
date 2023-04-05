package ibf2022.paf.day23workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.paf.day23workshop.model.OrderDetails;
import ibf2022.paf.day23workshop.repository.OrderDetailsRepository;

@RestController
@RequestMapping(path = "/order/total", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailsController {
    
    private OrderDetailsRepository repository;
    
    public OrderDetailsController(OrderDetailsRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "{orderId}")
    public ResponseEntity<String> getOrderDetailsInfo(@PathVariable Integer orderId) {

        OrderDetails orderDetails = repository.getOrderDetailsWithDiscount(orderId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(orderDetails.toJson().toString());
    }


}
