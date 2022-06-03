package com.test.app.service;

import com.test.app.entity.User;
import com.test.app.model.CreateUserDTO;

import java.util.List;

public interface UserService {

    /**
     * метод для отображения списка только классных преподавателей
     *
     * @param roleId айди роли
     * @return сипсок пользователей
     */
    List<User> getUserListByRoleId(long roleId);

    /**
     * удаление пользователя системы
     *
     * @param id айди пользователя
     */
    void removeUser(long id);

    /**
     * создание нового пользователя системы
     *
     * @param CreateUserDTO модель создания пользователя
     */
    void createUser(CreateUserDTO CreateUserDTO);

    /**
     * поиск пользователя по айди
     *
     * @param id айди пользователя
     * @return объект типа пользователь
     */
    User findById(long id);

}
