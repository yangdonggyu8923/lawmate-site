package site.lawmate.user.service;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.dto.PaymentDto;

import java.util.List;
import java.util.UUID;

public interface PaymentService extends CommandService<PaymentDto>, QueryService<PaymentDto> {
    // 결제 요청 데이터 조회
    PaymentDto findRequestDto(String orderUid);

    // 결제(콜백)
    IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request);

    public List<PaymentDto> getPaymentsByBuyerId(Long buyer);

    default site.lawmate.user.domain.model.Payment dtoToEntity(PaymentDto dto) {
        return site.lawmate.user.domain.model.Payment.builder()
                .paymentUid(UUID.randomUUID().toString())
                .status(dto.getStatus())
                .buyer(dto.getBuyer())
                .product(dto.getProduct())
                .build();
    }

    default PaymentDto entityToDto(site.lawmate.user.domain.model.Payment pay) {
        return PaymentDto.builder()
                .id(pay.getId())
                .paymentUid(UUID.randomUUID().toString())
                .status(pay.getStatus())
                .buyer(pay.getBuyer())
                .product(pay.getProduct())
                .build();
    }

}
