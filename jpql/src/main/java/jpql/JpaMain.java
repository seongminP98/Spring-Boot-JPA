package jpql;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

/*type query, query / 결과 조회
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member m"); //반환 타입이 명확하지 않을 경우

            List<Member> resultList = query1.getResultList(); //결과가 하나 이상일 때 리스트 반환
            //결과가 없으면 빈 리스트 반환
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }

            Member singleResult = query1.getSingleResult(); //결과가 하나 일 때
            //결과가 정확히 하나, 단일 객체 반환
            //없거나 여러개면? Exception터짐
            System.out.println("singleResult = " + singleResult);
*/
            //이름 기준 파라미터 바인딩. 위치기반은 사용하지 말기
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + result.getUsername());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
