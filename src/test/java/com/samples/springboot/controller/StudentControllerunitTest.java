package com.samples.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.samples.springboot.controller.StudentController;
import com.samples.springboot.model.Course;
import com.samples.springboot.service.StudentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser

public class StudentControllerunitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

@Test
public void createStudentCourse() throws Exception {
    // Create a mock Course object.
    Course course = new Course("Course5", "Java", "10 Steps",
            List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

    // Mock the studentService.addCourse method to return the mock Course object using Mockito.
    Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(Course.class))).thenReturn(course);

    //Use request builder to send a POST request to the /students/Student1/courses endpoint with the course details in the request body.
    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/Student1/courses")
            .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}")
            .contentType(MediaType.APPLICATION_JSON);
    
    //Execute the request using the mockMvc.perform method and capture the response.
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    
    //Extract the response status and verify that it is 201 CREATED.
    assertEquals(201, result.getResponse().getStatus());
    
    //Assert the location header in the response points to the new course URL.'
    assertEquals("http://localhost/students/Student1/courses/Course5", result.getResponse().getHeader("location"));

        }
}