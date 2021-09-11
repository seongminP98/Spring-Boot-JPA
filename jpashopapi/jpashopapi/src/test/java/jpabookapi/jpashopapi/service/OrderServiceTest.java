package jpabookapi.jpashopapi.service;

import jpabookapi.jpashopapi.domain.Address;
import jpabookapi.jpashopapi.domain.Member;
import jpabookapi.jpashopapi.domain.Order;
import jpabookapi.jpashopapi.domain.OrderStatus;
import jpabookapi.jpashopapi.domain.item.Book;
import jpabookapi.jpashopapi.domain.item.Item;
import jpabookapi.jpashopapi.exception.NotEnoughStockException;
import jpabookapi.jpashopapi.repository.OrderRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus()); //상품 주문 상태 : ORDER
        assertThat(1).isEqualTo(getOrder.getOrderItems().size()); //주문한 상품 종류 수가 정확
        assertThat(10000*orderCount).isEqualTo(getOrder.getTotalPrice()); //주문 가격은 가격*수량
        assertThat(8).isEqualTo(book.getStockQuantity()); //주문 수량만큼 재고가 줄어야 한다.
    }



    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when


        //then
        NotEnoughStockException ex = assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), orderCount);
        });
        assertThat(ex.getMessage()).isEqualTo("need more stock");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000,10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
        assertThat(10).isEqualTo(book.getStockQuantity());


    }


    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}