package site.lawmate.lawyer.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import site.lawmate.lawyer.domain.dto.PdfEditRequest;
import site.lawmate.lawyer.domain.dto.SavePdfRequest;

import java.io.File;
import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/pdfs")
public class PdfController {

    @PostMapping("/upload")
    public Mono<ResponseEntity<String>> uploadPdf(@RequestPart("file") FilePart filePart) {
        return filePart.transferTo(new File("uploads/" + filePart.filename()))
                .then(Mono.just(ResponseEntity.ok("File uploaded successfully")));
    }

    @PostMapping("/edit")
    public Mono<ResponseEntity<String>> editPdf(@RequestBody PdfEditRequest editRequest) {
        String filePath = editRequest.getFilePath();
        String editType = editRequest.getEditType();
        byte[] content = editRequest.decodeContent();

        return Mono.fromCallable(() -> {
            try (PDDocument document = PDDocument.load(new File(filePath))) {
                if ("addImage".equals(editType)) {
                    PDPage page = document.getPage(0);
                    PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, content, "image");

                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                        contentStream.drawImage(pdImage, 100, 100, 200, 200); // 이미지 위치와 크기 설정
                    }
                }

                document.save(filePath); // 편집된 PDF 저장
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error editing PDF", e);
            }
            return ResponseEntity.ok("PDF edited successfully");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<String>> savePdf(@RequestBody SavePdfRequest saveRequest) {
        // 편집된 PDF를 DB에 저장하는 로직 구현
        return Mono.just(ResponseEntity.ok("PDF saved successfully"));
    }
}
