package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본생성자 있어야함.
public class MemberDto {

    private String username;
    private int age;

    @QueryProjection //Q파일 생성. gradle 에서 compileQuerydsl
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
