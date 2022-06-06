package com.test.app.service.impl;


import com.test.app.entity.Student;
import com.test.app.entity.StudentClass;
import com.test.app.exception.NotFoundException;
import com.test.app.model.CreateStudentClassDTO;
import com.test.app.model.SortCriteria;
import com.test.app.repo.StudentClassRepo;
import com.test.app.service.StudentClassService;
import com.test.app.service.StudentService;
import com.test.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassRepo studentClassRepo;
    private final UserService userService;
    private final StudentService studentService;

    @Override
    public void removeStudentClass(long id) {
        if (studentClassRepo.existsById(id))
            throw new NotFoundException("Целевой класс не найден");

        studentClassRepo.deleteById(id);

    }

    @Override
    public Iterable<StudentClass> getStudentClassList(long id) {
        return studentClassRepo.findAll();
    }

    @Override
    public void createStudentClass(CreateStudentClassDTO cr) {

        var curator = userService.findById(cr.getCuratorId());
        var studentClassEntity = StudentClass.builder()
                .name(cr.getName())
                .curator(curator)
                .build();
        studentClassRepo.save(studentClassEntity);
    }

    @Override
    public List<StudentClass> getAllStudentClassesByCuratorId(long curatorId) {
        return studentClassRepo.findAllByCuratorId(curatorId);
    }

    @Override
    public List<Student> getStudentsByCuratorId(long curatorId) {
        var classes = getAllStudentClassesByCuratorId(curatorId);

        return classes.stream()
                .flatMap(el -> studentService.findStudentsByClassId(el.getId(), SortCriteria.SORT_STUDENT_CLASS).stream())
                .toList();
    }

}
