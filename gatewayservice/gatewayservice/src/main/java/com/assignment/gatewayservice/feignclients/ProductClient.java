package com.assignment.gatewayservice.feignclients;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.gatewayservice.util.AuthorizationUtil;
import com.assignment.gatewayservice.util.RestHttpClient;

@SuppressWarnings("unchecked")
@Service("product")
public class ProductClient<T> implements BaseClient<T> {

	@Autowired
	private DiscoveryClient eurekaClient;

	@Autowired
	private RestHttpClient restTemplate;

	@Autowired
	private AuthorizationUtil<T> authorizationUtil;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		requestData.put("session", authorizationUtil.createSession(requestData.get("token").toString()));
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<T> httpEntity = new HttpEntity<T>((T) requestData, httpHeaders);
		ResponseEntity<JSONObject> response = restTemplate.exchange(
				eurekaClient.getInstances("PRODUCTSERVICE").get(0).getUri() + "/product/fetch/?productId="+(int)requestData.get("productId"), HttpMethod.GET,
				httpEntity, JSONObject.class);
		return ResponseEntity.status(response.getStatusCode()).body((T) response.getBody());
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		return null;
	}

}
