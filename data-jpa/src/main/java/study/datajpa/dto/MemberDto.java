package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Member;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member) { //Dto 는 엔티티를 봐두 됨. 엔티티는 Dto 를 보면 안됨
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
