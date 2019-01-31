package com.codecool.shoppingcartservice.service;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.repository.CartRepository;
import com.katonarobert.microserviceproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Cart cart;

//    public void addItemToCart(Integer giftId) {
//        cart.addItemToCart(giftId);
//    }
//
//    public void removeItemFromCart(Integer giftId) {
//        cart.removeItemFromCart(giftId);
//    }



    public List<Product> getItemsById(Integer buyerId) {
        final String url =  "http://217.65.104.98/8051/get-products-by-product-id";
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> productList= cartRepository.findByBuyerId(buyerId).getProductInCart();
        List<Product> forObject = restTemplate.postForObject(url, productList, List.class);
        return forObject;
    }
}
