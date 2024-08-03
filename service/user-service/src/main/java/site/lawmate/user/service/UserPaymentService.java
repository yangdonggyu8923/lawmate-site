package site.lawmate.user.service;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.UserPayment;

import java.util.List;
import java.util.UUID;

public interface UserPaymentService extends CommandService<UserPaymentDto>, QueryService<UserPaymentDto> {
    // 결제 요청 데이터 조회
    UserPaymentDto findRequestDto(String orderUid);

    // 결제(콜백)
    IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request);

    public List<UserPaymentDto> getPaymentsByBuyerId(Long buyer);

    default UserPayment dtoToEntity(UserPaymentDto dto) {
        return UserPayment.builder()
                .paymentUid(UUID.randomUUID().toString())
                .status(dto.getStatus())
                .buyer(dto.getBuyer())
                .product(dto.getProduct())
                .build();
    }

    default UserPaymentDto entityToDto(UserPayment pay) {
        return UserPaymentDto.builder()
                .id(pay.getId())
                .paymentUid(UUID.randomUUID().toString())
                .status(pay.getStatus())
                .buyer(pay.getBuyer())
                .product(pay.getProduct())
                .build();
    }

}
