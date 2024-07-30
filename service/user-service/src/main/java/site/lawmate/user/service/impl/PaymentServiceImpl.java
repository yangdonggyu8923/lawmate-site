package site.lawmate.user.service.impl;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.dto.PaymentDto;
import site.lawmate.user.repository.PaymentRepository;
import site.lawmate.user.repository.UserRepository;
import site.lawmate.user.service.PaymentService;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository payRepository;
    private final IamportClient iamportClient;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Messenger save(PaymentDto dto) {
        log.info("Payment service 진입 성공: {}", dto);

        site.lawmate.user.domain.model.Payment payment = dtoToEntity(dto);
        site.lawmate.user.domain.model.Payment savedPayment = payRepository.save(payment);
        boolean exists = payRepository.existsById(savedPayment.getId());
        return Messenger.builder()
                .message(exists ? "SUCCESS" : "FAILURE")
                .build();
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
    public List<PaymentDto> findAll() {
        return payRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public Optional<PaymentDto> findById(Long id) {
        return payRepository.findById(id).map(i -> entityToDto(i));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDto> getPaymentsByBuyerId(Long buyerId) {
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
    public Messenger update(PaymentDto dto) {
        Optional<site.lawmate.user.domain.model.Payment> payment = payRepository.findById(dto.getId());
        if (payment.isPresent()) {
            site.lawmate.user.domain.model.Payment pay = payment.get();
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

    @Override
    public PaymentDto findRequestDto(String orderUid) {
        return null;
    }

    @Override
    public IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request) {
        return null;
    }

}
