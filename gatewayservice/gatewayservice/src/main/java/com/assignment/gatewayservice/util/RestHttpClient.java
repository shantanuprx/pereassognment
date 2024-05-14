package com.assignment.gatewayservice.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

@Component
public class RestHttpClient<T> {

	public ResponseEntity<T> sendRequest (String requestUrl) {
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, );
			Request request = new Request.Builder()
			  .url("localhost:8080/product/fetch?productId=1")
			  .method("GET", body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cookie", "JSESSIONID=98A4DC3EF17898F9C97CC689D9C33451")
			  .build();
			Response response = client.newCall(request).execute();
		} catch(Exception ex) {
			
		} finally {
			
		}
		
		return null;
		
	}
}
