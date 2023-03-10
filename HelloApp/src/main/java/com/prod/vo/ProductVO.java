package com.prod.vo;

import lombok.Data;

@Data
public class ProductVO {
	private String productCode;
	private String productName;
	private int originPrice;
	private int salePrice;
	private String productDesc;
	private double likeIt;
	private String image;
	
	
}
