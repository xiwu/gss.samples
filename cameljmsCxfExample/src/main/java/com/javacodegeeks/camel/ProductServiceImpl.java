package com.javacodegeeks.camel;

import java.util.HashMap;
import java.util.Map;

import com.javacodegeeks.ws.product_service.types.ProductResponse;

public class ProductServiceImpl {
    public ProductResponse getProductDetails(com.javacodegeeks.ws.product_service.types.ProductRequest request) {
    	System.out.println("=====request.id ======== " + request.getId());
    	System.out.println("=====request ======== " + request);
    	Product product = PRODUCT_DETAILS.get(request.getId());
        if (product == null) {
            throw new ProductNotFoundException(request.getId());
        }
//        try {
////			Thread.currentThread().sleep(1000*60*5);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        ProductResponse response = new ProductResponse();
        response.setId(product.id);
        response.setDescription(product.description);
        response.setPrice(product.price);
        return response;
    }
    
    private static Map<String, Product> PRODUCT_DETAILS = new HashMap<String, Product>();
    
    private static class Product {
    	private String id;
    	private String description;
    	private int price;
    	
    	Product(String id, String desc, int price) {
    		this.id = id;
    		this.description = desc;
    		this.price = price;
    	}
    }
    
    static {
    	PRODUCT_DETAILS.put("P01", new Product("P01", "Laptop", 40000));
    	PRODUCT_DETAILS.put("P02", new Product("P02", "Mobile", 14000));
    	PRODUCT_DETAILS.put("P03", new Product("P03", "Tablet", 30000));
    }
}
