package com.app.School_MIS.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void testAddStudent() {
        Student student = new Student("pascal", "pascal@gmail.com");
        when(studentRepository.save(student)).thenReturn(student);
        Student saved = studentService.addStudent(student);
        assertThat(saved.getName()).isEqualTo("pascal");
        verify(studentRepository, times(1)).save(student);
    }

}
