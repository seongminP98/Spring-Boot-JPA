package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //신규 등록
            em.persist(item);
        } else { //이미 있음. 업데이트 느낌낌
            Item merge = em.merge(item);//머지 : 준영속 상태 엔티티를 영속 상태로 만드는 것.
            //item은 영속성 상태로 변하지 않음. merge가 영속성 컨텍스트로 관리한다.
            //머지를 사용하면 모든 속성이 변경됨. 없으면 null로 바뀜. 주의!
            //최대한 머지 사용하지 말고 변경감지 사용하기.
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
