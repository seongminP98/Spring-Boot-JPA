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
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);



            em.flush();
            em.clear();

/*경로 표현식
            //String query = "select m.team From Member m"; //단일 값 연관 경로
            String query = "select m.username From Team t join t.members m"; //컬렉션 값 연관 경로 / 명식적 조인 사용
            Integer result = em.createQuery(query, Integer.class)
                    .getSingleResult();
            System.out.println("result = " + result);
*/
/*페치 조인 사용x , 지연 로딩
            String query = "select m From Member m";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
                //지연로딩이기 때문에 첫번째 루프를 돌 때 팀A를 가져옴.
                //회원1, 팀A(SQL)
                //회원2, 팀A(1차캐시)
                //회원3, 팀B(SQL)

                //회원 100명 -> N + 1 문제  => 페치 조인으로 해결
            }
*/
/*페치 조인
            String query = "select m From Member m join fetch m.team";

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
                //이미 조인으로 다 들고와서 여기서의 team은 프록시가 아님. 실제 엔티티가 담긴다.
                //지연 로딩해도 페치 조인이 우선임.
            }
*/
/*페치조인과 distinct
            String query = "select distinct t From Team t join fetch t.members";

            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "|members=" + team.getMembers().size());
                for(Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                    //distinct 사용하기 전 : 팀A가 중복해서 2줄 나옴.
                }
            }
*/
/*일반 조인.
            String query = "select t From Team t join t.members m";

            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "|members=" + team.getMembers().size());
                for(Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                    //distinct 사용하기 전 : 팀A가 중복해서 2줄 나옴.
                }
            }
*/
/*페치조인 한계. batchsize 사용
            String query = "select t From Team t";

            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            for (Team team : result) {
                System.out.println("team = " + team.getName() + "|members=" + team.getMembers().size());
                for(Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }
*/
/*엔티티 직접 사용 - 기본 키 값
            String query = "select m from Member m where m = :member";

            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("member", member1)
                    .getSingleResult();

            System.out.println("findMember = " + findMember);
*/
/*엔티티 직접 사용 - 외래 키 값
            String query = "select m from Member m where m.team = :team";

            List<Member> members = em.createQuery(query, Member.class)
                    .setParameter("team", teamA)
                    .getResultList();

            for (Member member : members) {
                System.out.println("member = " + member);
            }
*/
            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
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
