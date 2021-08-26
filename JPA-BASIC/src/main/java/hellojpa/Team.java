package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") //Member 클래스에 있는 team //mappedBy 있는건 읽기만 가능. team이 연관관계 주인.
    private List<Member> members = new ArrayList<>(); //초기화 해줌. 관례
/* Member 에서 연관관계 편의 메소드 사용. 하나만 사용.
    public void addMember(Member member) { //연관관계 편의 메소드
       member.setTeam(this);
        members.add(member);
    }
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
