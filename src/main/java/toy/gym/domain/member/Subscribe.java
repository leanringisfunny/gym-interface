package toy.gym.domain.member;

public enum Subscribe {
    ONE("1개월",1),
    THREE("3개월",3),
    SIX("6개월",6),
    NINE("9개월",9),
    YEAR("1년",12);

    private String month;
    private Integer value;

    Subscribe(String month, Integer value) {
        this.month = month;
        this.value = value;
    }
    public Integer getDuration(){
        return value;
    }

    public String getMonth() {
        return month;
    }
}
