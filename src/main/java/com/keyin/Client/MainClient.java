package com.keyin.Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String baseUrl = "http://localhost:8080";

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/city"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response from server:");

        // Create an instance of RestCLI and display the cities
        RestCLI restCLI = new RestCLI();
        restCLI.displayCities(response.body());
    }
}
