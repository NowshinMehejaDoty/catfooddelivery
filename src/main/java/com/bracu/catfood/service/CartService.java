package com.bracu.catfood.service;

import com.bracu.catfood.entity.Cart;
import com.bracu.catfood.entity.CartDetail;
import com.bracu.catfood.entity.Food;
import com.bracu.catfood.entity.Product;
import com.bracu.catfood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartDetailsRepository cartDetailsRepository;

    public List<Cart> getCartByUser(AuthUser authUser) {
        return cartRepository.findByUserIdAndIsPlaced(authUser.getUserId(), 0);
    }

    public List<CartDetail> getCartDetails(Integer cartId) {
        return cartDetailsRepository.findByCartId(cartId);
    }

    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Boolean addProductToCart(Integer productId, AuthUser user) {
        List<Cart> cart = cartRepository.findByUserIdAndIsPlaced(user.userId, 0);
        Product product = productRepository.findById(productId).orElse(null);
        Food food = product.getFood();
        Cart savedCart = null;
        if(cart.size() != 0) {
            Cart cartItem = cart.get(0);

            savedCart = cartRepository.save(cartItem);
        }
        else {
            Cart cartItem = new Cart();
            cartItem.setUser(userRepository.findById(user.userId).orElse(null));
            cartItem.setIsPlaced(0);
            savedCart = cartRepository.save(cartItem);
        }

        if(savedCart != null) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(savedCart);
            cartDetail.setFood(food);
            cartDetail.setProduct(product);
            cartDetailsRepository.save(cartDetail);
        }

        return true;
    }
}