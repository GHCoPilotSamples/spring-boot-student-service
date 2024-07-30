package com.samples.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.samples.springboot.model.Course;
import com.samples.springboot.model.Student;
import com.samples.springboot.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testRetrieveAllStudents() {
        List<Student> students = studentService.retrieveAllStudents();
        assertEquals(2, students.size());
    }

    @Test
    public void testRetrieveStudent() {
        Student student = studentService.retrieveStudent("Student1");
        assertNotNull(student);
        assertEquals("Student1", student.id());

        Student nonExistentStudent = studentService.retrieveStudent("NonExistent");
        assertNull(nonExistentStudent);
    }

    @Test
    public void testRetrieveCourses() {
        List<Course> courses = studentService.retrieveCourses("Student1");
        assertNotNull(courses);
        assertEquals(4, courses.size());

        List<Course> nonExistentCourses = studentService.retrieveCourses("NonExistent");
        assertNull(nonExistentCourses);
    }

    @Test
    public void testRetrieveCourse() {
        Course course = studentService.retrieveCourse("Student1", "Course1");
        assertNotNull(course);
        assertEquals("Course1", course.id());

        Course nonExistentCourse = studentService.retrieveCourse("Student1", "NonExistent");
        assertNull(nonExistentCourse);

        Course nonExistentStudentCourse = studentService.retrieveCourse("NonExistent", "Course1");
        assertNull(nonExistentStudentCourse);
    }

    @Test
    public void testAddCourse() {
        Course newCourse = new Course("Course5", "Java", "10 Steps",
                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course addedCourse = studentService.addCourse("Student1", newCourse);
        assertNotNull(addedCourse);
        assertNotNull(addedCourse.id());
        assertEquals("Java", addedCourse.name());

        Course nonExistentStudentCourse = studentService.addCourse("NonExistent", newCourse);
        assertNull(nonExistentStudentCourse);
    }
}