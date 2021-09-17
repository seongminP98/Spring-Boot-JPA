package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;


    /**
     * JPA 표준 스펙에 엔티티는 파라미터 없는 기본생성자가 하나 있어야한다.
     * access 레벨이 protected 까지 열어놔야 한다.
     * JPA가 프록시 기술을 쓸 때 private로 막아두면 안되기 때문에 protected까지 열어둔다.
     */
    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

}
