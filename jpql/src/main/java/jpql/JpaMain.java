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

            for(int i=0; i<100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }


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
/*이름 기준 파라미터 바인딩. 위치기반은 사용하지 말기
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + result.getUsername());
*/


            em.flush();
            em.clear();

/*엔티티 프로젝션
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .getResultList();
            //영속성 컨텍스트에서 관리됨.
            Member findMember = result.get(0);
            findMember.setAge(20);



            List<Team> result1 = em.createQuery("select m.team from Member m", Team.class)
                    .getResultList();
            List<Team> result2 = em.createQuery("select t from Member m join Team m.team t", Team.class)
                    .getResultList(); //이렇게 조인을 넣어서 사용하는게 좋음. join이 보이는게 좋음.
*/
/*임베디드 타입 프로젝션
            em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();
*/

/*프로젝션 여러 값 조회. Object[] 타입으로 조회. 별로 안좋음.
            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object[] result = resultList.get(0);
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);
*/
/*new 명령어로 조회. 단순 값을 DTO로 바로 조회. 패키지 명을 포함한 전체 클래스 명 입력. 순서와 타입이 일치하는 생성자 필요.
            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());
*/

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
