package toy.gym.domain.member;

public enum Subscribe {
    ONE("one",1),
    THREE("three",3),
    SIX("six",6),
    NINE("nine",9),
    YEAR("year",12);

    private String month;
    private Integer value;

    Subscribe(String month, Integer value) {
        this.month = month;
        this.value = value;
    }
    public Integer getDuration(){
        return value*30;
    }
}
