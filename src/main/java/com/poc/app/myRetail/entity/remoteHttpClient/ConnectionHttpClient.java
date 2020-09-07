/**
 * 
 */
package com.poc.app.myRetail.entity.remoteHttpClient;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.poc.app.myRetail.exception.MyRetailException;

@Component
public class ConnectionHttpClient {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${product-api-endpoint}")
	private String apiEndpointURL;

	private String product_URI = "/products/v3/";
	private String productName= null;

	public ConnectionHttpClient() {}
	
	public String getProductNameByRemoteCall(String productId) throws MyRetailException{

		try {
			logger.info("Inside ConnectionHttpClient().getProductNameByRemoteCall"+apiEndpointURL+product_URI+productId);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiEndpointURL+product_URI+productId)
			.queryParam("fields","descriptions")
			.queryParam("id_type", "TCIN")
			.queryParam("key", "43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
			logger.info("builder"+builder.build().encode().toUri());

			// Send request with GET method, and Headers.	
			String jsonResponse = restTemplate.getForObject(builder.build().encode().toUri(),String.class);

			if(jsonResponse != null) {
				JSONObject jsonObject=new JSONObject(jsonResponse);
				logger.debug("JSON Response from Remote Client  :" + jsonResponse.toString());

				if(jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description") != null) {
					JSONObject productDescription = jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description");
					productName = productDescription.getString("title");
				}
				else {
					logger.debug("Product title JSON value Unavailable in Product API");
					throw new MyRetailException(HttpStatus.NO_CONTENT.value() ,"The title does not exists for the product" );
				}
			}
		} catch (RestClientException e) {
			logger.debug("Product API unavailable  :" + apiEndpointURL+product_URI + productId);
			throw new MyRetailException(HttpStatus.NOT_FOUND.value() ,"Product Remote API unavailable");
		}
		return productName;
	}

}
