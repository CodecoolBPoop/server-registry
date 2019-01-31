package com.codecool.shoppingcartservice.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> productInCart = new ArrayList<>();
    private int buyerId;


    public void addItemToCart(Integer giftId) {
        productInCart.add(giftId);
    }

    public void removeItemFromCart(Integer giftId) {
        productInCart.remove(giftId);
    }

    public Cart(int buyerId) {
        this.buyerId = buyerId;
    }

    public Cart() {
    }

    public List<Integer> getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(List<Integer> productInCart) {
        this.productInCart = productInCart;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
