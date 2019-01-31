package com.codecool.shoppingcartservice.repository;

import com.codecool.shoppingcartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByBuyerId(int buyerId);

}
