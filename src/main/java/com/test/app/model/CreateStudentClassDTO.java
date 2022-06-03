package com.test.app.model;


import com.test.app.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateStudentClassDTO {

    private String name;

    private long curatorId;

}
