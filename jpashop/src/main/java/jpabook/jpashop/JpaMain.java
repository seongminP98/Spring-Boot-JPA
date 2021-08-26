package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
/*양방향 연관관계 편의 메소드 사용
            Order order = new Order();
            order.addOrderItem(new OrderItem());
*/

            //단방향일때도 문제없이 가능
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
            //이렇게.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

