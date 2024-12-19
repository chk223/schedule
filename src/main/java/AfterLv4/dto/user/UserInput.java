package AfterLv4.dto.user;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserInput {
    @NotBlank
    @Size(max = 4,message = "이름은 4자 이하여야 합니다.")
    private final String name;
    @NotBlank
    @Size(max = 20,message = "비밀번호는 20자 이하여야 합니다.")
    private final String password;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "이메일 형식이 유효하지 않습니다.")
    private final String email;

    public UserInput(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
