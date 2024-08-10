package site.lawmate.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.lawmate.user.domain.dto.LawPaymentDto;
import site.lawmate.user.domain.model.LawPayment;

import java.util.List;

public interface LawPaymentRepository extends JpaRepository<LawPayment, Long> {
    LawPayment findByImpUid(String impUid);

    @Query("SELECT p FROM law_payments p WHERE p.lawyerId = :lawyerId")
    List<LawPaymentDto> findByLawyerId(Long lawyerId);
}
