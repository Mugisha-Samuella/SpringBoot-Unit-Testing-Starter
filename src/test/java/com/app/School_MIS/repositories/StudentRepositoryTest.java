package com.app.School_MIS.repositories;

import com.app.School_MIS.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void shouldSaveStudent() {
        // given
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john@example.com");

        // when
        Student savedStudent = studentRepository.save(student);

        // then
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("John Doe");
        assertThat(savedStudent.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    public void shouldFindStudentByEmail() {
        // given
        Student student = new Student();
        student.setName("Jane Doe");
        student.setEmail("jane@example.com");
        entityManager.persist(student);
        entityManager.flush();

        // when
        Optional<Student> found = studentRepository.findByEmail(student.getEmail());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(student.getName());
        assertThat(found.get().getEmail()).isEqualTo(student.getEmail());
    }

    @Test
    public void shouldFindAllStudents() {
        // given
        Student student1 = new Student();
        student1.setName("Student 1");
        student1.setEmail("student1@example.com");

        Student student2 = new Student();
        student2.setName("Student 2");
        student2.setEmail("student2@example.com");

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.flush();

        // when
        List<Student> students = studentRepository.findAll();

        // then
        assertThat(students).hasSize(2);
        assertThat(students).extracting(Student::getName)
                          .containsExactlyInAnyOrder("Student 1", "Student 2");
    }

    @Test
    public void shouldReturnEmptyWhenEmailNotFound() {
        // when
        Optional<Student> notFound = studentRepository.findByEmail("nonexistent@example.com");

        // then
        assertThat(notFound).isEmpty();
    }
}