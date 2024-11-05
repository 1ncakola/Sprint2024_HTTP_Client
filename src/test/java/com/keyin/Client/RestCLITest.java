package com.keyin.Client;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestCLITest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent)); // Redirect output to the ByteArrayOutputStream
    }

    @Test
    public void testDisplayCities() {
        // Sample JSON response to test
        String jsonResponse = "[{\"id\":1,\"name\":\"New York\",\"state\":\"New York\",\"population\":8419000,\"airports\":[{\"id\":1,\"name\":\"JFK\",\"code\":\"JFK\"}]}," +
                "{\"id\":2,\"name\":\"San Francisco\",\"state\":\"California\",\"population\":883305,\"airports\":[]}]";

        // Create an instance of RestCLI
        RestCLI restCLI = new RestCLI();

        // Call the method to test
        restCLI.displayCities(jsonResponse);

        // Verify the output
        String output = outContent.toString();
        assertTrue(output.contains("List of Cities:"));
        assertTrue(output.contains("ID: 1"));
        assertTrue(output.contains("Name: New York"));
        assertTrue(output.contains("State: New York"));
        assertTrue(output.contains("Population: 8419000"));
        assertTrue(output.contains("Airports:"));
        assertTrue(output.contains("  - JFK (Code: JFK)"));

        // Clean up
        System.setOut(originalOut); // Restore original output
    }
}
