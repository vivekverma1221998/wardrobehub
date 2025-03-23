package com.wardrobehub.request;

import com.wardrobehub.model.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateProductReq {

    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String color;

    Set<Size> size = new HashSet<>();

    private String imageUrl;

    private String topLevelCategory;

    private String secondLevelCategory;

    private String thirdLevelCategory;
}
