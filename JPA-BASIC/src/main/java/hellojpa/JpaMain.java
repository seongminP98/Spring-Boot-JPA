package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

/*jpql사용
            List<Member> result = em.createQuery(
                    "select m From Member m where m.username like '%kim%'",
                    Member.class
            ).getResultList();
*/
/*Criteria 실무 사용x
            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);

            String username = "sdfasf";
            if(username != null) {
                cq = cq.where(cb.equal(m.get("username"), "kim"));
            }
            List<Member> resultList = em.createQuery(cq)
                    .getResultList();
*/
            em.createNativeQuery("select MEMBER_ID, city, street, zipcode USERNAME from MEMBER")
                    .getResultList();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
