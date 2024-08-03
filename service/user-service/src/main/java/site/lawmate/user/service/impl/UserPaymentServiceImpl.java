package site.lawmate.user.service.impl;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.User;
import site.lawmate.user.domain.model.UserPayment;
import site.lawmate.user.domain.vo.PaymentStatus;
import site.lawmate.user.repository.UserPaymentRepository;
import site.lawmate.user.repository.UserRepository;
import site.lawmate.user.service.UserPaymentService;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserPaymentServiceImpl implements UserPaymentService {
    private final UserPaymentRepository payRepository;
    private final IamportClient iamportClient;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Messenger save(UserPaymentDto dto) {
        log.info("Payment service 진입 성공: {}", dto);
        UserPayment payment = dtoToEntity(dto);
        UserPayment savedPayment = payRepository.save(payment);
        boolean exists = payRepository.existsById(savedPayment.getId());

        if (exists && payment.getStatus() == PaymentStatus.OK) {
            updateUserPoints(payment.getBuyer().getId(), payment.getAmount());
        }

        return Messenger.builder()
                .message(exists ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public UserPaymentDto findRequestDto(String orderUid) {
        return null;
    }

    @Override
    public IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request) {
        return null;
    }

    private void updateUserPoints(Long userId, Long amount) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPoint(user.getPoint() + amount);
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public Messenger delete(Long id) {
        payRepository.deleteById(id);
        return Messenger.builder()
                .message(payRepository.existsById(id) ? "FAILURE" : "SUCCESS")
                .build();
    }

    @Override
    public List<UserPaymentDto> findAll(PageRequest pageRequest) {
        return payRepository.findAll(pageRequest).stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<UserPaymentDto> findById(Long id) {
        return payRepository.findById(id).map(this::entityToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPaymentDto> getPaymentsByBuyerId(Long buyerId) {
        return payRepository.findByBuyerId(buyerId);
    }

    @Override
    public Messenger count() {
        return Messenger.builder()
                .message(payRepository.count() + "").build();
    }

    @Override
    public boolean existsById(Long id) {
        return payRepository.existsById(id);
    }

    @Transactional
    @Override
    public Messenger update(UserPaymentDto dto) {
        Optional<UserPayment> payment = payRepository.findById(dto.getId());
        if (payment.isPresent()) {
            UserPayment pay = payment.get();
            pay.setStatus(dto.getStatus());
            pay.setBuyer(dto.getBuyer());
            pay.setProduct(dto.getProduct());
            payRepository.save(pay);
            return Messenger.builder().message("SUCCESS").build();
        }
        return Messenger.builder()
                .message("FAILURE")
                .build();
    }


}
