package jpabookapi.jpashopapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //fk 이름
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();
    /**
     * cascade 는 persist를 전파한다.
     * orderItems 컬렉션에 있는 것들을 persist(order)만 하면 다 persist 됨.
     */

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id") //fk를 Order 테이블에 둠.
    private Delivery delivery;

    private LocalDateTime orderDate; //하이버네이트가 알아서 지원해줌. 그냥 쓰면됨

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 편의 메서드==// 양방향 연관관계에서 사용
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItems orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
