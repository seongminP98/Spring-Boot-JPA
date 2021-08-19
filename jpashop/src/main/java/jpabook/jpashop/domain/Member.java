package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //orders 테이블에 있는 member 필드에 의해 매핑되었다. 연관관계의 주인이 아님.
    private List<Order> order = new ArrayList<>(); //컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.

}
