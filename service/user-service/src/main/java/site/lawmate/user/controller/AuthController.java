package site.lawmate.user.controller;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.UserDto;
import site.lawmate.user.service.UserService;
import site.lawmate.user.domain.dto.LoginDTO;
import site.lawmate.user.domain.model.PrincipalUserDetails;
import site.lawmate.user.domain.model.UserModel;
import site.lawmate.user.domain.vo.Registration;
import site.lawmate.user.domain.vo.Role;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class AuthController {

    private final UserService service;

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDto dto) throws SQLException {
        log.info("Parameters received through controller: " + dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto dto) throws SQLException {
        Messenger messenger = service.login(dto);
        return ResponseEntity.ok(messenger);
    }
//
//    @PostMapping("/login/local")
//    public Mono<PrincipalUserDetails> login(@RequestBody LoginDTO dto) {
//        log.info("login: {}", dto);
//        return Mono.just(new PrincipalUserDetails(UserModel.builder()
//                .id("aaa1234")
//                .email(dto.getEmail())
//                .roles(List.of(Role.ROLE_USER))
//                .registration(Registration.LOCAL)
//                .build(), null));
//    }

}
