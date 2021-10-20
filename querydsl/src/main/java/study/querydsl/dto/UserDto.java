package study.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name; //별칭이 다름. Member 는 username 으로 되어있음.
    private int age;

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
