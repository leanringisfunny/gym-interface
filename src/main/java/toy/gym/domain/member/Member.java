package toy.gym.domain.member;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private Long password;
    private Subscribe subscribe;

    private Long initdate;
    private Long left;

}
