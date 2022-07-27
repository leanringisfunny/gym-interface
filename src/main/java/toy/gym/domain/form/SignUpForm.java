package toy.gym.domain.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import toy.gym.domain.member.Subscribe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class SignUpForm {
    @NotBlank
    private String memberName;
    @NotBlank
    private Long password;
    @NotBlank
    private Subscribe subscribe;

}
