package com.test.app.repo;

import com.test.app.entity.Student;
import com.test.app.entity.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepo extends CrudRepository<StudentClass, Long> {

}
