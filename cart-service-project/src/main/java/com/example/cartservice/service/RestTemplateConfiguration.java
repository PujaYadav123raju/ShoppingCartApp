package com.example.cartservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class RestTemplateConfiguration {
 
	@Bean
	public RestTemplate getRestTemplate() {
	   return new RestTemplate();
}}




	











	
	

	
	
	
	
	
	
	