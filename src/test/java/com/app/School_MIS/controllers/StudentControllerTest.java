package com.app.School_MIS.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.app.School_MIS.models.Student;
import com.app.School_MIS.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void testAddStudent() throws Exception {
        Student student = new Student("pascal", "pascals@gmail.com");
        when(studentService.addStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(
                post("/students").contentType("application/json").content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("pascal"));

    }
    @Test
    void testGetAllStudents() throws Exception {
        Student s1 = new Student("John Doe", "john@example.com");
        Student s2 = new Student("Jane Doe", "jane@example.com");
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
