package site.lawmate.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.UserPayment;

import java.util.List;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
    UserPayment findByPaymentUid(String paymentUid);

    @Query("SELECT p FROM user_payments p WHERE p.buyer.id = :buyerId")
    List<UserPaymentDto> findByBuyerId(Long buyerId);
}