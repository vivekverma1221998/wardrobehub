package com.wardrobehub.service;

import com.wardrobehub.exception.ProductException;
import com.wardrobehub.model.Cart;
import com.wardrobehub.model.User;
import com.wardrobehub.request.AddItemRequest;

public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId , AddItemRequest itemRequest) throws ProductException;

    public Cart findUserCart(Long userId);

}
