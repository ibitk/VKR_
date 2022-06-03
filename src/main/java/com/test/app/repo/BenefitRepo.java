package com.test.app.repo;

import com.test.app.entity.Benefit;
import com.test.app.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BenefitRepo extends CrudRepository<Benefit, Long> {

    Optional<Benefit> findByStudent(Student student);// все еще пробел

}
