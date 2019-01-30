package com.codecool.shoppingcartservice.controller;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import com.codecool.shoppingcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;

    @PostMapping(value = "/addItemToCart")
    public void addItemToCart(@RequestParam(value = "buyerId") Integer buyerId, @RequestParam(value = "giftId") Integer giftId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
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


    @DeleteMapping(value = "/itemsInCart/delete")
    public void deleteItemFromShoppingCart(@RequestParam(value = "buyerId") Integer buyerId, @RequestParam(value = "giftId") Integer giftId) {
        if (cartRepository.findByBuyerId(buyerId) != null) {
            cart = cartRepository.findByBuyerId(buyerId);
            cart.getGiftsInCart().remove(giftId);
        } else {
            return;
        }
    }

    @GetMapping(value = "/itemsInCart/user")
    public String getItemsInCart(@RequestParam(value = "buyerId") Integer buyerId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
            return "empty";
        } else {
            return cartRepository.findByBuyerId(buyerId).getGiftsInCart().toString();
        }
    }

}
