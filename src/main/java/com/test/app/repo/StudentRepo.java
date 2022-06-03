package com.test.app.repo;

import com.test.app.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

    List<Student> findAllByStudentClassId(long studentClass_id);

    Optional<Student> findByStudentClassIdAndLastName(long studentClass_id, String lastName);
}
