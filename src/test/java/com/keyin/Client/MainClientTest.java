package com.keyin.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MainClientTest {

    private HttpClient mockClient;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setup() {
        mockClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);
    }

    @Test
    public void testGetCities() throws IOException, InterruptedException {

        when(mockResponse.body()).thenReturn("[{\"name\": \"New York\"}, {\"name\": \"Los Angeles\"}]");
        when(mockClient.send(any(HttpRequest.class),any(HttpResponse.BodyHandler.class))).thenReturn(mockResponse);


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/cities")).GET().build();
        HttpResponse<String> response = mockClient.send(request, HttpResponse.BodyHandlers.ofString());


        assertEquals("[{\"name\": \"New York\"}, {\"name\": \"Los Angeles\"}]", response.body());
    }
}
