package com.test.app.service.impl;

import com.test.app.exception.NotFoundException;
import com.test.app.exception.WrongArgException;
import com.test.app.entity.Student;
import com.test.app.model.SortCriteria;
import com.test.app.repo.StudentRepo;
import com.test.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final Comparator<Student> lastNameComparator = Comparator.comparing(Student::getLastName);
    private final Comparator<Student> studentClassComparator = Comparator.comparing((student) -> student.getStudentClass().getName());

    private final StudentRepo repo;

    @Override
    public Student addStudent(Student student) {
        return repo.save(student);
    }

    @Override
    public Student modifyStudentInfo(Student student) {
        if (student == null || student.getId() == 0)
            throw new WrongArgException("Не задан идентификатор студента");

        if (repo.existsById(student.getId()))
            throw new NotFoundException("Целевой студент не найден");

        return repo.save(student);
    }

    @Override
    public void removeStudent(long id) {
        if (repo.existsById(id))
            throw new NotFoundException("Целевой студент не найден");

        repo.deleteById(id);
    }

    @Override
    public Student getStudentById(long studentId) {
        return repo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Целевой студент не найден"));
    }

    @Override
    public List<Student> findStudentsByClassId(long classId, SortCriteria sortCriteria) {
        List<Student> students = repo.findAllByStudentClassId(classId);

        switch (sortCriteria) {
            case SORT_ALPHABET -> students.sort(lastNameComparator);
            case SORT_STUDENT_CLASS -> students.sort(studentClassComparator);
        }


        return students;
    }

    @Override
    public Student findStudentByLastNameAndClass(long studentClass_id, String lastName) {
        return repo.findByStudentClassIdAndLastName(studentClass_id, lastName)
                .orElseThrow(() -> new NotFoundException("Целевой студент не найден"));
    }
}
