package com.test.app.service;

import com.test.app.entity.Student;
import com.test.app.entity.StudentClass;
import com.test.app.model.CreateStudentClassDTO;

import java.util.List;


public interface StudentClassService {

    /**
     * Метод удаляет весь класс, типо как отчислить(выпустить группу)
     *
     * @param id по id класса
     */
    void removeStudentClass(long id);

    /**
     * Метод для отображения списка классов при назначении студента
     *
     * @return возвращать список студентов
     */
    Iterable<StudentClass> getStudentClassList(long id);

    /**
     * метод для содания класса
     *
     * @param createStudentClassDTO модель оздания класса
     * @return созданный класс
     */
    void createStudentClass(CreateStudentClassDTO createStudentClassDTO);

    List<StudentClass> getAllStudentClassesByCuratorId(long curatorId);

    List<Student> getStudentsByCuratorId(long curatorId);

}
