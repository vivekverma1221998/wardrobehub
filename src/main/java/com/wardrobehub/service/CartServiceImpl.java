package com.wardrobehub.service;

import com.wardrobehub.exception.ProductException;
import com.wardrobehub.model.Cart;
import com.wardrobehub.model.CartItem;
import com.wardrobehub.model.Product;
import com.wardrobehub.model.User;
import com.wardrobehub.repository.CartRepository;
import com.wardrobehub.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;


    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();

        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest itemRequest) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);

        Product product = productService.findProductById(itemRequest.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart ,product ,itemRequest.getSize(), userId);

        if(isPresent == null){
            CartItem cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(itemRequest.getQuantity());
            cartItem.setUserId(userId);

            int price = itemRequest.getQuantity() * product.getDiscountedPrice();

            cartItem.setPrice(price);
            cartItem.setSize(itemRequest.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);

            cart.getCartItems().add(createdCartItem);
        }

        return "Item Added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice = 0;

        int totalDiscountedPrice = 0;

        int totalItem = 0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(totalPrice - totalDiscountedPrice);

        Cart createdCart = cartRepository.save(cart);

        return createdCart;
    }
}
