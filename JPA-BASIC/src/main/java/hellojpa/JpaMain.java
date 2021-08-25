package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
/* 등록
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);
*/
/* 수정
            Member findMember = em.find(Member.class, 1L); //멤버 찾기
            findMember.setName("HelloJPA");
*/
/*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
*/
/*
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            //영속
            em.persist(member);

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName()); //1차 캐시에서 가져옴.
*/
/*
            Member findMember1 = em.find(Member.class, 101L); //DB에서 가져와 영속성 컨텍스트에 올려놓음
            Member findMember2 = em.find(Member.class, 101L); //1차 캐시에서 가져옴.

            System.out.println("result = " + (findMember1 == findMember2)); //영속 엔티티의 동일성 보장
*/
/*쓰기 지연. 커밋하고 DB에 쿼리 날림.
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("======================");
*/
/*
            //데이터를 가져오고 값만 바꿔오면 수정됨. 변경감지(Dirty checking). 트랜잭션 커밋하는 시점에 업데이트 쿼리 날림.
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");
*/
/*
            Member member = new Member(202L, "member-202");
            em.persist(member);

            em.flush(); //플러시 강제로 호출. 이때 DB에 쿼리가 날라감. 1차캐시 유지됨.
            System.out.println("===================");
*/
/*
            Member member = em.find(Member.class, 150L);
            //member는 영속상태.
            member.setName("AAAA");

            //em.detach(member); //member는 준영속상태가 됨. 트랜잭션 커밋해도 아무일도 일어나지 않음. AAAA로 변경안됨.
            em.clear(); //영속성 컨텍스트를 완전히 초기화
            Member member2 = em.find(Member.class, 150L); //다시 영속성컨텍스트를 올림.
*/
            Member member = new Member();
            member.setUsername("C");

            System.out.println("====================");
            em.persist(member); //identity 전략을 사용하면 DB에 저장되어야 pk 값을 알 수 있기 때문에 커밋하기 전, 이 시점에 DB에 저장됨.
            //영속성 컨텍스트는 pk 값이 있어야 하기 때문.

            //SEQUENCE 전략을 사용할 때도 DB에 접근해서 pk값을 가져와야 함. 그래서 db에서 시퀀스 값을 얻어와서 member의 id값을 넣어줌.
            //아직 db에 쿼리는 날라가지 않음. 시퀀스 값만 가져와서 member.id만 정해줌.
            System.out.println("member.getId() = " + member.getId());
            System.out.println("====================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
