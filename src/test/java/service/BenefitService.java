package service;

import com.test.app.entity.Benefit;
import com.test.app.entity.BenefitType;
import com.test.app.model.CreateBenefitDTO;


public interface BenefitService {

    void revokeBenefit(long studentId);

    /**
     * Данный метод возвращает все типы бенефитов, что есть в системе
     *
     * @return коллекция типов бенефитов
     */
    Iterable<BenefitType> getBenefitTypeList();

    /**
     * Данный метод добавляет(создает) бенефит студенту
     *
     * @param creationRequest все данные о бенефите
     */
    void assignBenefit(CreateBenefitDTO creationRequest);

    /**
     * Данный метод возвращает бенефит студента, если студент не найден, то выбросится исключение
     *
     * @param studentId id студента
     * @return сущность бенефит для конкретного студента
     */
    Benefit getCurrentBenefit(long studentId);

}
