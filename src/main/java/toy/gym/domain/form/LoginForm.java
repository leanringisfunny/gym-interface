package toy.gym.domain.form;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;


@Data
public class LoginForm {
    @NotEmpty
    private Long password;
}
