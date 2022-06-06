package com.test.app.model;

import com.test.app.entity.Student;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentAttendanceModel {

    private long studentId;
    private String studentName;
    private String groupName;
    private List<Integer> visitedDays;

    public static StudentAttendanceModel fromStudent(Student student) {
        return StudentAttendanceModel.builder()
                .studentId(student.getId())
                .studentName(student.getName())
                .groupName(student.getStudentClass().getName())
                .build();
    }


}
