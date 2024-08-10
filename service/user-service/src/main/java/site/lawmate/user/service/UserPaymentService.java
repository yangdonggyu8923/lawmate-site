package site.lawmate.user.service;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.UserPaymentDto;
import site.lawmate.user.domain.model.PaymentCallbackRequest;
import site.lawmate.user.domain.model.UserPayment;

import java.io.IOException;
import java.util.List;

public interface UserPaymentService extends CommandService<UserPaymentDto>, QueryService<UserPaymentDto> {
    // 결제 요청 데이터 조회
    UserPaymentDto findRequestDto(String orderUid);

    // 결제(콜백)
    IamportResponse<Payment> paymentByCallback(PaymentCallbackRequest request);

    List<UserPaymentDto> findByBuyerId(Long buyerId);

    Messenger cancelPayment(UserPaymentDto dto) throws IamportResponseException, IOException;

    void addUserPoints(Long id, Long amount);

    Messenger subtractUserPoints(UserPaymentDto dto);

    Messenger confirmPayment(UserPaymentDto dto);

    default UserPayment dtoToEntity(UserPaymentDto dto) {
        return UserPayment.builder()
                .lawyer(dto.getLawyer())
                .impUid(dto.getImpUid())
                .status(dto.getStatus())
                .buyer(dto.getBuyer())
                .amount(dto.getAmount())
                .product(dto.getProduct())
                .build();
    }

    default UserPaymentDto entityToDto(UserPayment pay) {
        return UserPaymentDto.builder()
                .id(pay.getId())
                .lawyer(pay.getLawyer())
                .impUid(pay.getImpUid())
                .status(pay.getStatus())
                .buyer(pay.getBuyer())
                .amount(pay.getAmount())
                .product(pay.getProduct())
                .build();
    }

}
