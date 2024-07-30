package com.samples.springboot.controller;

import com.samples.springboot.StudentServicesApplication;
import com.samples.springboot.model.Course;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = StudentServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {

//Generate a test for the StudentControllerIT class in Java using Spring Boot. The test should use @LocalServerPort, TestRestTemplate, and HttpHeaders. It should verify that the endpoint /students/Student1/courses/Course1 returns the expected JSON response.
//The StudentControllerIT class is in the com.samples.springboot.controller package.
//The StudentControllerIT class is annotated with @SpringBootTest, and the webEnvironment attribute is set to RANDOM_PORT.
//The test method is annotated with @Test.
//The StudentControllerIT class is in the com.samples.springboot.controller package.
//The StudentControllerIT class is annotated with @SpringBootTest, and the webEnvironment attribute is set to RANDOM_PORT.
//The test method is annotated with @Test.
//The test method uses the @LocalServerPort annotation to inject the port number of the running server.
//The test method creates an instance of TestRestTemplate and HttpHeaders.
//The test method sends an HTTP GET request to the /students/Student1/courses/Course1 endpoint using the TestRestTemplate.
//The test method verifies that the response contains the expected JSON data.

  @LocalServerPort
  private int port;

   TestRestTemplate restTemplate = new TestRestTemplate();

   HttpHeaders headers = new HttpHeaders();

  @Test
  public void testGetCourseDetails() throws JSONException{
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
      createURLWithPort("/students/Student1/courses/Course1"),
      HttpMethod.GET, entity, String.class);

      String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";

    JSONAssert.assertEquals(expected, response.getBody(), false);

    }

   // Generate a JUnit test method for the StudentController to test the creation of a new course for a student. The test should:
//1. Create a mock Course object.
//2. Mock the studentService.addCourse method to return the mock Course object.
//3. Send a POST request to the /students/Student1/courses endpoint with the course details in the request body.
//4. Verify that the response status is 201 CREATED.
//5. Verify that the Location header in the response points to the new course URL.



    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
  }
