package site.lawmate.lawyer.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SavePdfRequest {
    private String pdfBytes; // Base64 인코딩된 PDF 바이트 배열
    private String fileName;
    private String lawyerId; // 파일을 업로드한 사용자의 ID

    public byte[] decodePdfBytes() {
        return Base64.getDecoder().decode(pdfBytes);
    }
}
