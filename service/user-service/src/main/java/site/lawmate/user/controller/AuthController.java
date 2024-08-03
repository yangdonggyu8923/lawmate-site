package site.lawmate.user.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.LoginDTO;
import site.lawmate.user.domain.dto.OAuth2UserDto;
import site.lawmate.user.domain.dto.UserDto;
import site.lawmate.user.service.UserService;
import site.lawmate.user.domain.dto.LoginDTO;

import java.sql.SQLException;

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
        log.info("user save 파라미터: {}", dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping(path = "/login/local")
    public ResponseEntity<Messenger> login(@RequestBody UserDto dto) throws SQLException {
        Messenger messenger = service.login(dto);
        return ResponseEntity.ok(messenger);
    }

    @PostMapping("/oauth2/{registration}")
    public ResponseEntity<LoginDTO> oauthJogin(@RequestBody OAuth2UserDto dto) {
        log.info("user oauth2 파라미터: {} ", dto);
        return ResponseEntity.ok(service.oauthJoin(dto));
    }

}
