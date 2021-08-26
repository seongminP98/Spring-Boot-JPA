package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

/*
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //조인하는 컬럼. FK 가 TEAM_ID. 연관관계 매핑.
    private Team team;
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

/*    public Team getTeam() {
        return team;
    }

    //setter대신 이름바꿔 사용.
    public void changeTeam(Team team) { //연관관계 편의 메소드. 주인쪽만 아니라 주인이 아닌쪽도 값 세팅.
        this.team = team;
        team.getMembers().add(this); //team.getMembers().add(member)
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/
}

