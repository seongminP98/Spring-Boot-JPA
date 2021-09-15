package jpabookapi.jpashopapi.api;

import jpabookapi.jpashopapi.domain.Address;
import jpabookapi.jpashopapi.domain.Order;
import jpabookapi.jpashopapi.domain.OrderStatus;
import jpabookapi.jpashopapi.repository.OrderRepository;
import jpabookapi.jpashopapi.repository.OrderSearch;

import jpabookapi.jpashopapi.repository.order.simplequery.OrderSimpleQueryDto;
import jpabookapi.jpashopapi.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch()); //빈객체. 검색조건이 없기 때문에 주문을 다 들고옴.
        for (Order order : all) {
            order.getMember().getName(); //getMember()까지는 프록시 객체. getName()을 하면 진짜 객체를 가져옴. LAZY 강제 초기화
            order.getDelivery().getAddress();
        }

        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        //ORDER 2개
        //N + 1 문제. 처음 1 orders 가져오는 쿼리1번. 결과 2(N)개 + 회원 N + 배송 N
        List<Order> orders = orderRepository.findAll(new OrderSearch());

        //루프 2번
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {

        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) { //Dto가 엔티티를 파라미터로 받는건 상관없다.
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }
}
