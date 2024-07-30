package site.lawmate.user.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.lawmate.user.component.Messenger;
import site.lawmate.user.domain.dto.IssueDto;
import site.lawmate.user.service.IssueService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/issues")
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class IssueController {
    private final IssueService issueService;

//    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public SseEmitter subscribe(){
//        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
//        issueService.addEmitter(emitter);
//        issueService.sendEvents();
//        return emitter;
//    }

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<Messenger> save(@RequestBody IssueDto dto) throws SQLException {
        log.info("issue save 파라미터: {} ", dto);
        return ResponseEntity.ok(issueService.save(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<IssueDto>> findAll() throws SQLException {
        log.info("findAll issue 진입 성공");
        return ResponseEntity.ok(issueService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Messenger> delete(@PathVariable("id") Long id) throws SQLException {
        log.info("delete issue id: {} ", id);
        return ResponseEntity.ok(issueService.delete(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Messenger> update(@RequestBody IssueDto dto) {
        log.info("update issue 진입 성공: {} ", dto.toString());
        return ResponseEntity.ok(issueService.update(dto));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<IssueDto>> findById(@PathVariable("id") Long id) throws SQLException {
        log.info("issue 정보 조회 진입 id: {} ", id);
        return ResponseEntity.ok(issueService.findById(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Messenger> count() throws SQLException {
        log.info("issue count 진입 성공");
        return ResponseEntity.ok(issueService.count());
    }

    @GetMapping("/search")
    public ResponseEntity<Boolean> existsById(@RequestParam("id") Long id) throws SQLException {
        log.info("issue search 진입 성공 id: {} ", id);
        return ResponseEntity.ok(issueService.existsById(id));
    }
}
