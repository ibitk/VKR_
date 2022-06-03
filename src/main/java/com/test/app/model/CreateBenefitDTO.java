package com.test.app.model;
// модель бенефита
import com.test.app.entity.Student;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class CreateBenefitDTO {

    private Student student;

    private int benefitTypeId;

    private ZonedDateTime startAt;

    private ZonedDateTime endAt;

    private double amount;
}
