package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * JPA 표준 스펙에 엔티티는 파라미터 없는 기본생성자가 하나 있어야한다.
     * access 레벨이 protected 까지 열어놔야 한다.
     * JPA가 프록시 기술을 쓸 때 private로 막아두면 안되기 때문에 protected까지 열어둔다.
     */
/* @NoArgsConstructor(access = AccessLevel.PROTECTED)
    protected Member() {
    }
*/

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    //연관관계 편의 메서드드
   public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
