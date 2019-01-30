package com.codecool.shoppingcartservice.service;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;

    public void addItemToCart(Integer giftId) {
        cart.addItemToCart(giftId);
    }

    public void removeItemFromCart(Integer giftId) {
        cart.removeItemFromCart(giftId);
    }
}
