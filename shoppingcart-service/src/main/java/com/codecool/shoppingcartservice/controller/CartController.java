package com.codecool.shoppingcartservice.controller;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.model.Product;
import com.codecool.shoppingcartservice.repository.CartRepository;
import com.codecool.shoppingcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/addItemToCart")
    public void addItemToCart(@RequestBody HashMap<String,String> product) {
        String productId = product.get("productId");
        String buyerId = product.get("buyerId");
        if (cartRepository.findByBuyerId(Integer.parseInt(buyerId)) == null) {
            cart = new Cart(Integer.parseInt(buyerId));
            cart.addItemToCart(Integer.parseInt(productId));
            cartRepository.save(cart);
        } else {
            cart = cartRepository.findByBuyerId(Integer.parseInt(buyerId));
            cart.setId(Integer.parseInt(buyerId));
            cart.addItemToCart(Integer.parseInt(productId));
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

    @GetMapping(value = "/itemsInCart/user/{buyerId}")
    public List<Product> getItemsInCart(@PathVariable(value = "buyerId") int buyerId) {
        if (cartRepository.findByBuyerId(buyerId) == null) {
            return null;
        } else {
            return cartService.getItemsById(buyerId);
        }

    }

}
