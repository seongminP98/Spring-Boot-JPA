package jpabookapi.jpashopapi.api;

import jpabookapi.jpashopapi.domain.Order;
import jpabookapi.jpashopapi.repository.OrderRepository;
import jpabookapi.jpashopapi.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * XtoOne 관계
 * Order
 * Order -> Member      : ManyToOne
 * Order -> Delivery    : OneToOne
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch()); //빈객체. 검색조건이 없기 때문에 주문을 다 들고옴.
        for (Order order : all) {
            order.getMember().getName(); //getMember()까지는 프록시 객체. getName()을 하면 진짜 객체를 가져옴. LAZY 강제 초기화
            order.getDelivery().getAddress();
        }

        return all;
    }
}
