package com.hcl.client.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.client.dto.ProductResponseDto;
import com.hcl.client.dto.ProductsDto;
import com.hcl.client.model.Product;
import com.hcl.client.repository.ClientRepositorys;

@Service
public class ClientServiceImpl implements ClientServices {

	private static final String urls = "http://localhost:7118/delete/{id}";
	@Autowired
	private ClientRepositorys clientRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper om;

	@Bean
	private RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public Product createProduct(Product product) {

		String url1 = "http://localhost:7118/create";
		HttpEntity<Product> entity = new HttpEntity<Product>(product);
		ResponseEntity<Product> products = getRestTemplate().exchange(url1, HttpMethod.POST, entity, Product.class);
		System.out.println(products.getBody());
		return clientRepository.save(products.getBody());
	}

	@Override
	public ProductResponseDto findProduct(Long productId) { // TODO Auto-generated

		String url1 = "http://localhost:7118/select/" + productId + "";
		/*
		 * Product p = getRestTemplate().getForObject(url1, Product.class);
		 * ProductResponseDto product= new ProductResponseDto();
		 * product.setpId(p.getpId()); product.setpName(p.getpName());
		 * product.setPrice(p.getPrice()); product.setQuantity(p.getQuantity());
		 */

		ResponseEntity<Product> pro = getRestTemplate().getForEntity(url1, Product.class);

		ProductResponseDto product = new ProductResponseDto();
		product.setpId(pro.getBody().getpId());
		product.setpName(pro.getBody().getpName());
		product.setPrice(pro.getBody().getPrice());
		product.setQuantity(pro.getBody().getQuantity());
		
		Product p= new Product();
		p.setpName("Chair");
		p.setpId(10L);
		
		return product;
	}

	@Override
	public ProductsDto getAllProduct() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange("http://localhost:7118/getAll", HttpMethod.GET, entity,
				String.class);

		System.out.println(result.getBody());

		ResponseEntity<List<Product>> results = restTemplate.exchange("http://localhost:7118/getAll", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Product>>() {
				});

		System.out.println(results.getBody());

		return null;
	}

	@Override
	public String deleteProduct(Long productId) {

		Map<String, String> params = new HashMap<String, String>();
		Product p = new Product();
		p.setpId(2L);
		// HttpEntity<Product> entity= new HttpEntity<Product>(p);

		params.put("id", "8");
		// getRestTemplate().exchange("http://localhost:7118/delete/7",
		// HttpMethod.DELETE,entity,String.class);
		getRestTemplate().delete(urls, params);
		return "Delete Success...";
	}

}
