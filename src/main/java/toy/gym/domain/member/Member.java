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

    private Calendar initdate;
    private Calendar exdate;

    public Member (){}

    public Member( String name, Long password, Subscribe subscribe, Calendar initdate,Calendar exdate) {
        this.name = name;
        this.password = password;
        this.subscribe = subscribe;
        this.initdate = initdate;
        this.exdate = exdate;
    }
}
