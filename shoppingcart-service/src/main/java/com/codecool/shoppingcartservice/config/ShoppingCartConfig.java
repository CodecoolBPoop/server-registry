package com.codecool.shoppingcartservice.config;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ShoppingCartConfig {

    @Autowired
    CartRepository cartRepository;
    @PostConstruct
    public void init(){

        Cart cart = new Cart(1);
        cart.addItemToCart(1);
        cart.addItemToCart(2);
        cartRepository.save(cart);
    }
}
