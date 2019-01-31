package com.codecool.shoppingcartservice.controller;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import com.codecool.shoppingcartservice.service.CartService;
import com.katonarobert.microserviceproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/addItemToCart")
    public void addItemToCart(@RequestParam(value = "buyerId") Integer buyerId, @RequestParam(value = "productId") Integer productId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
            cart = new Cart(buyerId);
            cart.addItemToCart(productId);
            cartRepository.save(cart);
        } else {
            cart = cartRepository.findByBuyerId(buyerId);
            cart.setId(buyerId);
            cart.addItemToCart(productId);
            cartRepository.save(cart);
        }
    }


    @DeleteMapping(value = "/itemsInCart/delete")
    public void deleteItemFromShoppingCart(@RequestParam(value = "buyerId") Integer buyerId, @RequestParam(value = "productId") Integer productId) {
        if (cartRepository.findByBuyerId(buyerId) != null) {
            cart = cartRepository.findByBuyerId(buyerId);
            cart.getProductInCart().remove(productId);
        } else {
            return;
        }
    }

    @GetMapping(value = "/itemsInCart/user")
    public List<Product> getItemsInCart(@RequestParam(value = "buyerId") Integer buyerId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
            return null;
        } else {
            return cartService.getItemsById(buyerId);
        }

    }

}
