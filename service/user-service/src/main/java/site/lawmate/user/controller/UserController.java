package site.lawmate.user.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.QuestionDto;
import site.lawmate.user.domain.dto.UserDto;
import site.lawmate.user.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class UserController {
    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() throws SQLException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/searchEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam("email") String email) {
        log.info("Parameter information of existsEmail: " + email);
        Boolean flag = service.existsByEmail(email);
        log.info("existsEmail : " + email);
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/search")
    public ResponseEntity<Boolean> existsById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable("id") Long id) {
        log.info("Parameter information of findById: " + id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Messenger> update(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Messenger> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("authorization") String accessToken) {
        var flag = service.logout(accessToken);
        return ResponseEntity.ok(flag);
    }

    @PutMapping("/modifyPoint")
    public ResponseEntity<Messenger> modifyPoint(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.modifyPoint(dto));
    }

}