package com.lab11_requests;

import com.lab11_requests.entities.Person;
import com.lab11_requests.error_handler.ErrorHandler;
import com.lab11_requests.requests.FriendRequests;
import com.lab11_requests.requests.PersonRequests;
import com.lab11_requests.requests.Requests;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
public class Lab11RequestsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lab11RequestsApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ErrorHandler());

		Requests requests = new PersonRequests(restTemplate);
		requests.execute();

		requests = new FriendRequests(restTemplate);
		requests.execute();
	}
}
