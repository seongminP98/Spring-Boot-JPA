package jpabookapi.jpashopapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //(Embedded, Embeddable 하나만 있어도 됨. 여기선 둘 다 써줬음.)
    private Address address;

    @OneToMany(mappedBy = "member") //Order 의 member 필드
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 초기화!


}
