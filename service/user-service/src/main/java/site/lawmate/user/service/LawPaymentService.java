package site.lawmate.user.service;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import site.lawmate.user.domain.dto.LawPaymentDto;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.LawPayment;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.model.UserPayment;

import java.util.List;
import java.util.UUID;

public interface LawPaymentService extends CommandService<LawPaymentDto>, QueryService<LawPaymentDto> {
    // 결제 요청 데이터 조회
    UserPaymentDto findRequestDto(String orderUid);

    // 결제(콜백)
    IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request);

    public List<LawPaymentDto> getPaymentsByLawyerId(Long lawyerId);

    default LawPayment dtoToEntity(LawPaymentDto dto) {
        return LawPayment.builder()
                .lawyerId(dto.getLawyerId())
                .premiumUid(UUID.randomUUID().toString())
                .build();
    }

    default LawPaymentDto entityToDto(LawPayment pay) {
        return LawPaymentDto.builder()
                .id(pay.getId())
                .lawyerId(pay.getLawyerId())
                .premiumUid(UUID.randomUUID().toString())
                .build();
    }

}
