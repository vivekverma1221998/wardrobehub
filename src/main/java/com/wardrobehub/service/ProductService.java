package com.wardrobehub.service;

import com.wardrobehub.exception.ProductException;
import com.wardrobehub.model.Product;
import com.wardrobehub.request.CreateProductReq;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductReq createProductReq);

    public String deleteProduct(Long productId) throws ProductException;


    public Product updateProduct(Long productId, Product productReq) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category) throws ProductException;


    public Page<Product> getAllProduct(String category , List<String> colors, List<String> sizes, Integer minPrice , Integer maxPrice,
    Integer minDiscount , String sort , String stock ,Integer pageNumber, Integer pageSize);


}
