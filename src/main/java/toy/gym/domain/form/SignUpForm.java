package toy.gym.domain.form;

import lombok.Data;
import toy.gym.domain.member.Subscribe;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private Long password;
    @NotEmpty
    private Subscribe subscribe;

}
