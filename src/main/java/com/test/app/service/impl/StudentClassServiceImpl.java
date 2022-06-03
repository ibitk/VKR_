package com.test.app.service.impl;


import com.test.app.entity.StudentClass;
import com.test.app.exception.NotFoundException;
import com.test.app.model.CreateStudentClassDTO;
import com.test.app.repo.StudentClassRepo;
import com.test.app.service.StudentClassService;
import com.test.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentClassServiceImpl implements StudentClassService {

    private final StudentClassRepo studentClassRepo;
    private final UserService userService;

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

}
