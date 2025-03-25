package com.wardrobehub.service;

import com.wardrobehub.exception.ProductException;
import com.wardrobehub.model.Review;
import com.wardrobehub.model.User;
import com.wardrobehub.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest request, User user ) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
