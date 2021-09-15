package jpabookapi.jpashopapi.api;

import jpabookapi.jpashopapi.domain.Order;
import jpabookapi.jpashopapi.domain.OrderItem;
import jpabookapi.jpashopapi.repository.OrderRepository;
import jpabookapi.jpashopapi.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1()  { //주문과 아이템 정보까지
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) { //객체 초기화. LAZY 상태이기 때문에 이렇게해서 실제 객체를 가져옴.
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems(); //orderItems 초기화
            orderItems.stream().forEach(o -> o.getItem().getName()); //item 각각의 이름도 초기화
        }
        return all;
    }
}
