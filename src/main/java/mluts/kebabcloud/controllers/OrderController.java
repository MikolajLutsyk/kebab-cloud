package mluts.kebabcloud.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mluts.kebabcloud.domain.KebabOrder;
import mluts.kebabcloud.domain.Users;
import mluts.kebabcloud.interfaces.OrderRepository;
import mluts.kebabcloud.propholders.OrderProps;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("kebabOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps props;

    public OrderController(OrderRepository orderRepository, OrderProps props){
        this.orderRepository = orderRepository;
        this.props = props;
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal Users user, Model model){
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid KebabOrder order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal Users user){

        if(errors.hasErrors()){
            return "orderForm";
        }

        //setting user to order by retrieving it from authentication as principal via annotation
        //authentication is an object retrieved from securitycontextholfer object
        order.setUser(user);

        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}