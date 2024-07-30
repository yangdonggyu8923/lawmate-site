package site.lawmate.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.lawmate.user.domain.dto.PaymentDto;
import site.lawmate.user.domain.model.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentUid(String paymentUid);

    @Query("SELECT p FROM payments p WHERE p.buyer.id = :buyerId")
    List<PaymentDto> findByBuyerId(Long buyerId);
}