package com.codecool.shoppingcartservice.service;

import com.codecool.shoppingcartservice.model.Cart;
import com.codecool.shoppingcartservice.model.Product;
import com.codecool.shoppingcartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
        final String url =  "http://localhost:8051/get-products-by-product-id";
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> productList= cartRepository.findByBuyerId(buyerId).getProductInCart();
        List forObject = restTemplate.postForObject(url, productList,List.class);

        return forObject;
    }
//    public ShitWishOrder handleGetOrderBy(long id) {
//        ShitWishOrder order = orderRepository.findById(id).orElse(null);
//
//        if (order != null) {
//
//            String productsByOrderIdUri = MsUriCreator.buildCustomUri(
//                    MsPort.PRODUCT.getPortNumber(),
//                    MsRequestPath.PRODUCTS.getPath(),
//                    MsRequestPath.ORDER.getPath(),
//                    String.valueOf(id)
//            );
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            try {
//                ResponseEntity<Set<Product>> responseEntity = restTemplate.exchange(
//                        productsByOrderIdUri,
//                        HttpMethod.GET,
//                        null,
//                        new ParameterizedTypeReference<Set<Product>>() {
//                        }
//                );
//
//                order.setProductSet(responseEntity.getBody());
//
//            } catch (ResourceAccessException rae) { // todo: delete later
//                Set<Product> mockProducts = createMockProducts();
//                order.setProductSet(mockProducts);
//
//            }
//        }
//
//        return order;
}
