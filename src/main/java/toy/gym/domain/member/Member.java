package toy.gym.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


@Getter@Setter
public class Member {
    private Long id;
    private String name;
    private Long password;
    private Subscribe subscribe;

    private String initdate;
    private String exdate;

    public Member (){}

    public Member( String name, Long password, Subscribe subscribe, String initdate,String exdate) {
        this.name = name;
        this.password = password;
        this.subscribe = subscribe;
        this.initdate = initdate;
        this.exdate = exdate;
    }
}
