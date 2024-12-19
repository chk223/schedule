package AfterLv4.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateInput {
    @Size(max=4,message = "이름은 4글자 이하여야 합니다.")
    private final String name;
    private final String password;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "이메일 형식이 유효하지 않습니다.")
    private final String email;

    @JsonCreator
    public UserUpdateInput(@JsonProperty("name") String name, @JsonProperty("password")String password, @JsonProperty("email")String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
