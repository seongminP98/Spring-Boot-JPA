package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);

            em.persist(member2);



            em.flush();
            em.clear();

            //String query = "select m.team From Member m"; //단일 값 연관 경로
            String query = "select m.username From Team t join t.members m"; //컬렉션 값 연관 경로 / 명식적 조인 사용
            Integer result = em.createQuery(query, Integer.class)
                    .getSingleResult();
            System.out.println("result = " + result);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
