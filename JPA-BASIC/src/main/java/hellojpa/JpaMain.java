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
            //저장
/*
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member); //연관관계의 주인이 Member의 team이다. 그래서 이거만 하면 db에 저장안됨. 아래처럼 해야함.

            em.persist(team);
*/
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //setter대신 changeTeam 등으로 사용
            em.persist(member);

//            team.getMembers().add(member); //jpa에서 이값을 안씀. 읽기전용이기 때문에. //연관관계 편의 메소드에 적용.
            //양방향 매핑 시 그냥 양쪽에 값을 넣어준다.

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            List<Member> members = findTeam.getMembers();
            System.out.println("==============");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("==============");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
