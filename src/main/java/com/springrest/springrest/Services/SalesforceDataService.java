package com.springrest.springrest.Services;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springrest.springrest.Authenticator.SalesforceAuthenticator;

@Service
public class SalesforceDataService {

	public Map getSalesforceData(String query) {
		SalesforceAuthenticator salesforceAuthenticator = SalesforceAuthenticator.getSalesforceToken();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
			final String baseUrl = salesforceAuthenticator.instanceUrl + "/services/data/v52.0/query/?q="
					+ encodedQuery;
			URI uri = new URI(baseUrl);
			System.out.println("uri" + uri);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", salesforceAuthenticator.accessToken));
			HttpEntity<?> request = new HttpEntity<Object>(headers);
			System.out.println("uri" + request);
			ResponseEntity<Map> response = null;
			try {
				response = restTemplate.exchange(uri, HttpMethod.GET, request, Map.class);
			} catch (HttpClientErrorException e) {
				System.out.println(e.getMessage());
			}
			return response.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Collections.emptyMap();
	}
}