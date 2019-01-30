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
    private List<Integer> giftsInCart = new ArrayList<>();
    private int buyerId;


    public void addItemToCart(Integer giftId) {
        giftsInCart.add(giftId);
    }

    public void removeItemFromCart(Integer giftId) {
        giftsInCart.remove(giftId);
    }

    public Cart(int buyerId) {
        this.buyerId = buyerId;
    }

    public Cart() {
    }

    public List<Integer> getGiftsInCart() {
        return giftsInCart;
    }

    public void setGiftsInCart(List<Integer> giftsInCart) {
        this.giftsInCart = giftsInCart;
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
