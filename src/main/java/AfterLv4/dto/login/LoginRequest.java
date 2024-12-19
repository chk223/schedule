package AfterLv4.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private final String name;
    @NotBlank
    private final String password;
}
