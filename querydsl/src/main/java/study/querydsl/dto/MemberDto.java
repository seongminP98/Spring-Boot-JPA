package study.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본생성자 있어야함.
public class MemberDto {

    private String username;
    private int age;


    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
