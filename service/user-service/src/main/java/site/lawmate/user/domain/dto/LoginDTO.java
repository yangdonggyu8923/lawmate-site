package site.lawmate.user.domain.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
