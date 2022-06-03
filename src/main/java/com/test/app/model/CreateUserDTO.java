package com.test.app.model;

import com.test.app.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {

    private int userId;

    private String name;

    private String login;

    private String passwordHash;

    private Role roleId;

}
