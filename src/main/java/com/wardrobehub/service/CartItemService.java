package com.wardrobehub.service;

import com.wardrobehub.exception.CartItemException;
import com.wardrobehub.exception.UserException;
import com.wardrobehub.model.Cart;
import com.wardrobehub.model.CartItem;
import com.wardrobehub.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId , Long id , CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart , Product product , String size , Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException , UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
