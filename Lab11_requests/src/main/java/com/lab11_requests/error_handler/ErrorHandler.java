package com.lab11_requests.error_handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;


public class ErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("STATUS CODE OF ERROR: " + response.getStatusCode());
    }
}
