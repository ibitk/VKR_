package com.test.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CanteenManagerAttendanceReport {

    private String groupName;
    private int totalAttendance;
    private int sirot;
    private int maloimuchi;
    private int richBoys;

    public void incrementSirot() {
        this.sirot++;
    }

    public void incrementMaloimuchi() {
        this.maloimuchi++;
    }

    public void incrementRichBoys() {
        this.richBoys++;
    }

    public void incrementTotal() {
        this.totalAttendance++;
    }


}
