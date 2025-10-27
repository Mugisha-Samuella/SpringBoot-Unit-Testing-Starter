package com.app.School_MIS.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.School_MIS.models.Student;
import com.app.School_MIS.repositories.StudentRepository;
import static org.assertj.core.api.Assertions.assertThat;
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddStudent() {
        Student student = new Student("pascal", "pascal@gmail.com");
        when(studentRepository.save(student)).thenReturn(student);
        Student saved = studentService.addStudent(student);
        assertThat(saved.getName()).isEqualTo("pascal");
        verify(studentRepository, times(1)).save(student);
    }
    @Test
    void testGetAllStudents() {
        Student student1 = new Student("pascal", "pascal@gmail.com");
        Student student2 = new Student("pascal", "pascal@gmail.com");
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));
        List<Student> students = studentService.getAllStudents();
        assertThat(students).hasSize(2).contains(student1, student2);
    }
    @Test
    void testGetStudentByEmail() {
        Student student = new Student("pascal", "pascal@gmail.com");
        when(studentRepository.findByEmail("pascal@gmail.com")).thenReturn(Optional.of(student));
        Student found = studentService.getStudentByEmail("pascal@gmail.com");
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("pascal@gmail.com");
    }
}
