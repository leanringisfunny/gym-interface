package toy.gym.domain.form;


import lombok.Data;

@Data


public class MembersForm {
    private Long id;
    private String name;
    private Long duration;

    public MembersForm() {
    }

    public MembersForm(Long id, String name, Long duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
}
