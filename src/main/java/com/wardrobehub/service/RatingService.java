package com.wardrobehub.service;

import com.wardrobehub.exception.ProductException;
import com.wardrobehub.model.Rating;
import com.wardrobehub.model.User;
import com.wardrobehub.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req , User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);


}
