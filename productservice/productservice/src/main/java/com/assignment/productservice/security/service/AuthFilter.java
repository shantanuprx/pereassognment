package com.assignment.productservice.security.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.productservice.constants.GatewayServiceConstants;
import com.assignment.productservice.util.JedisClientHelper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
/**
 * Implementation of web filter. Responsible for checking whether token is valid or not.
 */
@Slf4j
@Component
public class AuthFilter implements Filter {

	@Autowired
	private JedisClientHelper jedisClientHelper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(
				(HttpServletRequest) request);
		String requestString = IOUtils.toString(cachedBodyHttpServletRequest.getReader());
		JSONObject jsonObject = new JSONObject(requestString);
		log.debug("Parsed request is {} ", jsonObject);
		if (jsonObject.has(GatewayServiceConstants.TOKEN)) {
			if (Boolean.parseBoolean(jedisClientHelper.getValue(jsonObject.getString(GatewayServiceConstants.TOKEN)))) {
				jedisClientHelper.extendTokenLife(jsonObject.getString(GatewayServiceConstants.TOKEN));
				chain.doFilter(cachedBodyHttpServletRequest, response);
			} else {
				((HttpServletResponse) response).sendError(401, GatewayServiceConstants.TOKEN_EXPIRED);
			}
		} else {
			((HttpServletResponse) response).sendError(401, GatewayServiceConstants.TOKEN_NOT_AVAILABLE);
		}
	}
}
