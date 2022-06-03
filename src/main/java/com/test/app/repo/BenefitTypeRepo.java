package com.test.app.repo;

import com.test.app.entity.BenefitType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitTypeRepo extends CrudRepository<BenefitType, Long> {
}
