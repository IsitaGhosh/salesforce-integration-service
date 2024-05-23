package com.springrest.springrest.Authenticator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SalesforceAuthenticator {

	private static SalesforceAuthenticator salesforceAuthenticator = null;
	public static String accessToken;
	public static String instanceUrl;

	private SalesforceAuthenticator() {
		try {
			final String baseUrl = "https://login.salesforce.com/services/oauth2/token";
			URI uri = new URI(baseUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("username", "username here");
			params.add("password", "password here");
			params.add("client_secret", "client_secret here");
			params.add("client_id here",
					"client_secret here");
			params.add("grant_type", "password");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
					headers);

			RestTemplate restTemplate = new RestTemplate();
			System.out.println("response");
			ResponseEntity<Map> response = restTemplate.postForEntity(uri, request, Map.class);

			Map<String, String> responseBody = response.getBody();

			accessToken = responseBody.get("access_token");
			instanceUrl = responseBody.get("instance_url");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static SalesforceAuthenticator getSalesforceToken() {
		try {
			if (salesforceAuthenticator == null) {
				salesforceAuthenticator = new SalesforceAuthenticator();
				return salesforceAuthenticator;
			} else {
				return salesforceAuthenticator;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
}
