package com.test.app.service.impl;

import com.test.app.entity.User;
import com.test.app.exception.NotFoundException;
import com.test.app.model.CreateUserDTO;
import com.test.app.repo.UserRepo;
import com.test.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<User> getUserListByRoleId(long roleId) {
        return userRepo.getUserByRoleId(roleId);
    }

    @Override
    public void removeUser(long id) {
        if (userRepo.existsById(id)) {
            throw new NotFoundException("Преподаватель не найден");
        }
        userRepo.deleteById(id);
    }

    @Override
    public void createUser(CreateUserDTO createUserDTO) {
        var userEntity = User.builder()
                .name(createUserDTO.getName())
                .id(createUserDTO.getUserId())
                .login(createUserDTO.getLogin())
                .passwordHash(createUserDTO.getPasswordHash())
                .role(createUserDTO.getRoleId())
                .build();
        userRepo.save(userEntity);

    }

    @Override
    public User findById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        return userRepo.getUserByLogin(username)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }
}
