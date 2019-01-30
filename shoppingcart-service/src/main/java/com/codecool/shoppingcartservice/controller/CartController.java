package com.codecool.shoppingcartservice.controller;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import com.codecool.shoppingcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;

    @PostMapping(value = "/addItemToCart/user/{buyerId}/product/{id}")
    public void addItemToCart(@PathVariable(value = "buyerId") Integer buyerId, @PathVariable(value = "id") Integer giftId) {
        if (cartRepository.findByBuyerIdAndId(buyerId, giftId) == null) {
            cart = new Cart(buyerId);
            cart.addItemToCart(giftId);
            cartRepository.save(cart);
        } else {
            cart = cartRepository.findByBuyerId(buyerId);
            cart.setId(buyerId);
            cart.addItemToCart(giftId);
            cartRepository.save(cart);
        }
    }

    @GetMapping(value = "/itemsInCart")
    public String getItemsInCart(@RequestParam(value = "buyerId") Integer buyerId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
            return "empty";
        } else {
            return cartRepository.findByBuyerId(buyerId).getGiftsInCart().toString();
        }
    }

}
