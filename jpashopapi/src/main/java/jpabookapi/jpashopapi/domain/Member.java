package jpabookapi.jpashopapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded //(Embedded, Embeddable 하나만 있어도 됨. 여기선 둘 다 써줬음.)
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //Order 의 member 필드
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 초기화!


}
