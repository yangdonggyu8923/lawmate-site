package site.lawmate.user.service.impl;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.LawPaymentDto;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.LawPayment;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.repository.LawPaymentRepository;
import site.lawmate.user.service.LawPaymentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LawPaymentServiceImpl implements LawPaymentService {
    private final LawPaymentRepository payRepository;

    @Transactional
    @Override
    public Messenger save(LawPaymentDto dto) {
        log.info("premium 결제 service 진입 성공: {}", dto);
        LawPayment payment = dtoToEntity(dto);
        LawPayment savedPayment = payRepository.save(payment);
        boolean exists = payRepository.existsById(savedPayment.getId());
        return Messenger.builder()
                .message(exists ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public Messenger delete(Long id) {
        payRepository.existsById(id);
        return Messenger.builder()
                .message(payRepository.existsById(id) ? "FAILURE" : "SUCCESS")
                .build();
    }

    @Override
    public Messenger update(LawPaymentDto lawPaymentDto) {
        if (payRepository.existsById(lawPaymentDto.getId())) {
            LawPayment lawPayment = dtoToEntity(lawPaymentDto);
            payRepository.save(lawPayment);
            return Messenger.builder()
                    .message("SUCCESS")
                    .build();
        } else {
            return Messenger.builder()
                    .message("FAILURE")
                    .build();
        }
    }

    @Override
    public UserPaymentDto findRequestDto(String impUid) {
        return null;
    }

    @Override
    public IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request) {
        return null;
    }

    @Override
    public List<LawPaymentDto> getPaymentsByLawyerId(Long lawyerId) {
        return payRepository.findByLawyerId(lawyerId);
    }

    @Override
    public List<LawPaymentDto> findAll(PageRequest pageRequest) {
        return payRepository.findAll(pageRequest).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LawPaymentDto> findById(Long id) {
        Optional<LawPayment> payment = payRepository.findById(id);
        return payment.map(this::entityToDto);
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

}
