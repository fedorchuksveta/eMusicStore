package com.emusicstore.controller;

import com.emusicstore.model.Customer;
import com.emusicstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer/cart")
public class CartController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(@AuthenticationPrincipal User activeUser, Model model) {
        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
        int cartId = customer.getCart().getCartId();
        model.addAttribute("cartId", cartId);
        model.addAttribute("cartItems", customer.getCart());
        return "cart";
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable (value = "cartId") int cartId, Model model){
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
