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
public class PdfEditRequest {
        private String filePath; // 편집할 PDF 파일 경로
        private String editType; // 편집 타입 (예: "addImage", "addText")
        private String content; // 추가할 내용 (이미지의 경우 Base64 인코딩된 이미지 데이터, 텍스트의 경우 추가할 텍스트)
    // 이미지 데이터인 경우 Base64 디코딩 메서드
    public byte[] decodeContent() {
        return Base64.getDecoder().decode(content);
    }
    }
