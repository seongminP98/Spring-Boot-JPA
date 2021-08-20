package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
/*
    @PersistenceContext //JPA의 EntityManager 주입. 스프링이 해줌.
    private EntityManager em;
    //@RequiredArgsConstructor 사용하면 스프링 부트가 해줌. 스프링부트가 @Autowired도 injection 되게 해줌.
 */

    public void save(Member member) {
        em.persist(member); //persist 하면 persistContext 에 member객체를 넣는다. 나중에 transaction 이 commmit 되는 시점에 DB에 반영된다.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); //단건 (조회 타입,PK)
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //JPQL은 대상이 테이블이 아니라 엔티티가 된다.
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
