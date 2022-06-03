package service.impl;


import com.test.app.entity.Benefit;
import com.test.app.entity.BenefitType;
import com.test.app.exception.NotFoundException;
import com.test.app.model.CreateBenefitDTO;
import com.test.app.repo.BenefitRepo;
import com.test.app.repo.BenefitTypeRepo;
import service.BenefitService;
import service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitTypeRepo benefitTypeRepo;
    private final BenefitRepo benefitRepo;
    private final StudentService studentService;

    @Override
    public void revokeBenefit(long studentId) {

    }

    @Override
    public Iterable<BenefitType> getBenefitTypeList() {

        return benefitTypeRepo.findAll();
    }

    @Override
    public void assignBenefit(CreateBenefitDTO req) {

        var benefitType = getBenefitType(req.getBenefitTypeId());
        var benefitEntity = Benefit.builder()
                .amount(req.getAmount())
                .endedAt(req.getEndAt())
                .startedAt(req.getStartAt())
                .student(req.getStudent())
                .benefit(benefitType)
                .build();

        benefitRepo.save(benefitEntity);
    }


    @Override
    public Benefit getCurrentBenefit(long studentId) {

        var student = studentService.getStudentById(studentId);

        return benefitRepo.findByStudent(student).get();
    }

    private BenefitType getBenefitType(long id) {
        return benefitTypeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Бенефит не найден"));
    }
}
