package com.samples.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.samples.springboot.StudentServicesApplication;

@SpringBootApplication
public class StudentServiceApplicationTests {

//Create a test to check if context loads without any issues
    @Test
    public void contextLoads() {
    }
    @Test
    public void testMain() {
        StudentServicesApplication.main(new String[] {});
    }

    
}
