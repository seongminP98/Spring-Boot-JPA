package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            /*임베디드 타입, 값 타입과 불변 객체
            /**
             * member1, member2 둘 다 같은 address 값 타입을 사용하면.
             * 난 member1의 city만 바꿀려고 했는데 member2의 city까지 바뀜.
             * side effect
             * 대신 값을 복사해서 사용해야 함.

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

*/

            Member member = new Member();
            member.setUsername("user1");
            member.setHomeAddress(new Address("homeCity", "street1", "10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");
/*값 타입 컬렉션 사용 addressHistory
            member.getAddressHistory().add(new Address("old1", "street1", "10000"));
            member.getAddressHistory().add(new Address("old2", "street1", "10000"));
            //값 타입 컬렉션을 따로 persist 하지 않아도 member를 저장할 때 같이 들어감.
            //값 타입 컬렉션도 본인 스스로 라이프 사이클이 없음. member에 소속됨.
*/

            member.getAddressHistory().add(new AddressEntity("old1","street","10000"));
            member.getAddressHistory().add(new AddressEntity("old2","street","10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==================START====================");
            Member findMember = em.find(Member.class, member.getId());
            //값 타입 컬렉션은 지연로딩.

//            //homeCity -> newCity //값 타입은 통으로 바꿔야 함.
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(),a.getZipcode()));
//
//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

/*addressHistory를 값 타입 컬렉션 사용
            //컬렉션은 대부분 대상을 찾을 때 equals를 사용한다. equals 구현해야함. 오버라이드로
            findMember.getAddressHistory().remove(new Address("old1", "street1", "10000"));
            findMember.getAddressHistory().add(new Address("newCity1", "street1", "10000"));
*/



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
