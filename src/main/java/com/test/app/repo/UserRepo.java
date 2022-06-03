package com.test.app.repo;

import com.test.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    List<User> getUserByRoleId(long roleId);

}