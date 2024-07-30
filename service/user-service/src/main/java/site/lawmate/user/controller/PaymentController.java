package site.lawmate.user.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.vo.PaymentStatus;
import site.lawmate.user.domain.dto.PaymentDto;
import site.lawmate.user.service.PaymentService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/payment")
@Slf4j
@Controller
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class PaymentController {
    private final PaymentService paymentService;

    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/save")
    public ResponseEntity<Messenger> savePayment(@RequestBody PaymentDto dto) {
        log.info("payment save 파라미터: {} ", dto);
        return ResponseEntity.ok(paymentService.save(dto));
    }

    @PostMapping("/status")
    public ResponseEntity<String> paymentStatus(@RequestBody PaymentStatus status) {
        log.info("payment status: {}", status);
        if (status == PaymentStatus.OK) {
            // 결제 성공 시 처리할 로직 작성
            return new ResponseEntity<>("Payment SUCCESS", HttpStatus.OK);
        } else {
            // 결제 실패 시 처리할 로직 작성
            return new ResponseEntity<>("Payment FAILURE", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verifyIamport/{imp_uid}")
    public ResponseEntity<?> paymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
        log.info("imp_uid={}", imp_uid);
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(imp_uid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PaymentDto>> findById(@PathVariable("id") Long id) {
        log.info("payment 정보 조회 진입 id: {} ", id);
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Messenger> update(@RequestBody PaymentDto dto) {
        log.info("update payment 진입 성공: {} ", dto.toString());
        return ResponseEntity.ok(paymentService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Messenger> delete(@PathVariable("id") Long id) {
        log.info("delete payment id: {} ", id);
        return ResponseEntity.ok(paymentService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<PaymentDto>> findAll() {
        log.info("findAll payment 진입 성공");
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping(path = "/buyer/{buyerId}")
    public ResponseEntity<List<PaymentDto>> findByBuyerId(@PathVariable("buyerId") Long buyerId) {
        log.info("payment 정보 조회 진입 유저 id: {} ", buyerId);
        return ResponseEntity.ok(paymentService.getPaymentsByBuyerId(buyerId));
    }

}
