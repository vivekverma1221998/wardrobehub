package com.wardrobehub.exception;

import com.wardrobehub.service.CartItemService;

public class CartItemException extends Exception {
    public CartItemException(String msg){
        super(msg);
    }
}
