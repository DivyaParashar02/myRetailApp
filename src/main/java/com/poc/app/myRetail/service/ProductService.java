package com.poc.app.myRetail.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.poc.app.myRetail.entity.Product;
import com.poc.app.myRetail.entity.helper.ProductHelper;
import com.poc.app.myRetail.entity.remoteHttpClient.ConnectionHttpClient;
import com.poc.app.myRetail.exception.MyRetailException;
import com.poc.app.myRetail.repository.ProductRepository;
import com.poc.app.myRetail.response.vo.ProductVO;



@Service
public class ProductService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
		
	@Autowired
	ConnectionHttpClient connectHttpClient;
	
	@Autowired
	ProductHelper helperObject;
	
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductVO getProductById(String productId) throws MyRetailException {
		logger.info("Inside ProductService().getProductById");
		
		Product product = new Product();
		String productName=null;
		try {
		//retrive from MongoDB
		product = productRepository.findProductByproductId(productId);
		if(product == null) {
			logger.debug("Product Not Found Exception while fetching product data from DB ");
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(),"Product not found in DB");
		}
		
		//Retrieve title from product API
		//commented below LIne as API is not available
		 //productName = connectHttpClient.getProductNameByRemoteCall(productId);
		 productName = "The Big Lebowski (Blu-ray)(Widescreen)";
		}catch(MyRetailException e) {
			throw e; 
		}
		return helperObject.generateProductResponse(product,productName);

	}

	public void updateProductById(ProductVO productVO) throws MyRetailException{
		try {
		Product product =helperObject.getProductDomainObject(productVO);
		productRepository.save(product);
		} catch (Exception exception) {
			logger.debug("Product Not Found Exception while doing update " + exception);
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(),"Product not found while update");
		}
	}

}
