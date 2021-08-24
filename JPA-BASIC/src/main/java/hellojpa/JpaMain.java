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

            //데이터를 가져오고 값만 바꿔오면 수정됨. 변경감지(Dirty checking). 트랜잭션 커밋하는 시점에 업데이트 쿼리 날림.
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ"); //



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
