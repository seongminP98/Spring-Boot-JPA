package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /**
             * member1, member2 둘 다 같은 address 값 타입을 사용하면.
             * 난 member1의 city만 바꿀려고 했는데 member2의 city까지 바뀜.
             * side effect
             * 대신 값을 복사해서 사용해야 함.
             */
            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());
            member.setHomeAddress(newAddress); //값을 바꿀려면 다시 만들어서 통으로 바꿈.


            Address copyAddress  = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

//            member.getHomeAddress().setCity("newCity");
//세터지움. 아니면 private으로 세터를 만들어둠.


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
