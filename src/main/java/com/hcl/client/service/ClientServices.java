package com.hcl.client.service;




import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.client.dto.ProductResponseDto;
import com.hcl.client.dto.ProductsDto;
import com.hcl.client.model.Product;

@Service
public interface ClientServices {

	public Product createProduct(Product product);
	
	 public ProductResponseDto findProduct(Long productId); 
	 public ProductsDto getAllProduct(); 
	 public String deleteProduct(Long productId);
	 
	
}
