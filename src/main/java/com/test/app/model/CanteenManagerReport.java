package com.test.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CanteenManagerReport {

    private long studentId;
    private String studentName;
    private double currentBenefit;
    private double totalCharge;
    private String periodStart;
    private String periodEnd;
    private int benefitType;

}
