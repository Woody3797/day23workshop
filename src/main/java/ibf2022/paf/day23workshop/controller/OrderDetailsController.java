package ibf2022.paf.day23workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.paf.day23workshop.model.OrderDetails;
import ibf2022.paf.day23workshop.repository.OrderDetailsRepository;

@Controller
@RequestMapping(path = "/order/total", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderDetailsController {
    
    private OrderDetailsRepository repository;
    
    public OrderDetailsController(OrderDetailsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getOrderForm(Model model, @ModelAttribute OrderDetails orderDetails) {
        model.addAttribute("display", false);
        model.addAttribute("error", false);
        model.addAttribute("orderDetails", orderDetails);
        return "index";
    }

    @GetMapping(path = "/")
    public String getOrderForm2(Model model, @ModelAttribute OrderDetails orderDetails, @RequestParam(required = false) Integer orderId) {
        if (orderId == null) {
            model.addAttribute("error", false);
            model.addAttribute("display", false);
            return "index2";
        }
        try {
            orderDetails = repository.getOrderDetailsWithDiscount(orderId);
            model.addAttribute("display", true);
            model.addAttribute("orderDetails", orderDetails);
            return "index2";
        } catch (NullPointerException e) {
            model.addAttribute("error", true);
            return "index2";
        }
    }

    @GetMapping(path = "{orderId}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getOrderDetailsInfo(Model model, @PathVariable Integer orderId) {

        model.addAttribute("display", true);
        OrderDetails orderDetails = repository.getOrderDetailsWithDiscount(orderId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(orderDetails.toJson().toString());
    }


}
