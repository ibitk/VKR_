package service;

import com.test.app.entity.Student;
import com.test.app.model.SortCriteria;

import java.util.List;

public interface StudentService {
    /**
     * метод добавляет студента
     *
     * @param student объект типа студент
     * @return возвращает студента
     */
    Student addStudent(Student student);

    /**
     * метод изменения параментров студента
     *
     * @param student объект типа студент
     * @return студента как объект типа студент
     */
    Student modifyStudentInfo(Student student);

    /**
     * удаление студента
     *
     * @param id айди студента
     */
    void removeStudent(long id);

    /**
     * метод поиска студента по айди
     *
     * @param studentId айди студента
     * @return студента как объект типа студент
     */
    Student getStudentById(long studentId);

    /**
     * вывод списка студентов по группе
     *
     * @param classId      айди группы
     * @param sortCriteria сортировка по алфавиту и по ?????????????? вроде что-то сгруппой но как если мы их уже по группе вытащили
     * @return список студентов
     */
    List<Student> findStudentsByClassId(long classId, SortCriteria sortCriteria);

    /**
     * метод вывода студента по фамилии внутри группы
     *
     * @param studentClass_id номер группы
     * @param lastName        фамилия студента
     * @return студента как объект типа студент
     */
    Student findStudentByLastNameAndClass(long studentClass_id, String lastName);


}
