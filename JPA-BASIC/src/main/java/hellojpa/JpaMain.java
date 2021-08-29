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
/*프록시
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            em.persist(member3);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member2.getId());
            Member m3 = em.getReference(Member.class, member3.getId());
            System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass())); //true
            System.out.println("m1 == m3: " + (m1.getClass() == m3.getClass()));
            System.out.println("m1 == Member: " + (m3 instanceof Member));
            //지금 여기선 다르다는걸 인지? 할 수 있지만, 실제로는 어떤 로직(함수)로 구현되어서 프록시객체가 넘어올지 실제 객체가 넘어 올 지 알 수 없다.
            //그래서 instance of 사용.





            System.out.println("m1 = " + m1.getClass());
            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());
            //m1과 reference 둘다 프록시가 아니라 Member이다.
            String username = reference.getUsername();
            System.out.println("username = " + username);
            System.out.println("reference = " + reference.getClass());



            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println(refMember.getId());
            System.out.println("isLoaded: " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //프록시 초기화 됨? 여기선 안됨. false

            System.out.println("refMember = " + refMember.getClass()); //Proxy

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getClass()); //Member

            System.out.println("refMember == findMember: " + (refMember == findMember)); //JPA에서는 같아야함.
            //그래서 findMember도 프록시로 나옴. 프록시가 한번 조회되면 em.find에서 프록시가 반환됨.


            System.out.println("isLoaded: " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //프록시 초기화 됨? 됨. true.
            //em.find(Member.class, member1.getId()); 호출하면서 프록시 초기화 됐을듯..?


//            Member findMember = em.find(Member.class, member.getId());
            Member findMember3 = em.getReference(Member.class, member3.getId());
            //getReference를 호출하는 시점에는 DB에 쿼리를 안함. 이 값이 실제 사용되는 시점에
            //JPA가 DB에 쿼리를 날린다.

            System.out.println("findMember = " + findMember3.getClass());

            System.out.println("findMember.Id = " + findMember3.getId());
            //여기선 DB에 쿼리 안날림. em.persist(member) 를 거치면 ID가 들어가게 된다. 이 ID값을 사용.


            System.out.println("findMember.username = " + findMember3.getUsername());
            //Username을 알기 위해서 DB에 쿼리문 날려 조회.
  */
/*지연로딩과 즉시로딩

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamA");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member.getId()); //JPA가 조인해서 가져옴

            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();
            //SQL문 그대로 실행.
            //SQL : select * from Member
            //SQL : select * from Team where TEAM_ID = xx
            /**
             * 즉시로딩 EAGER에서의
             * N+1 문제. : 처음 JPQL쿼리를 날리고(1), 그 다음 N개의 쿼리를 날림.
             * 위에선 1이 members 가져오는 쿼리
             * N은 member1과 member2에 있는 각각의 team 쿼리.
             * 그래서 총 N+1의 쿼리를 날림.
             * 데이터 많아지면? 성능 저하
             *
             * LAZY로 하면 안나옴(1인 쿼리 하나만나옴). 왜? team은 프록시로 잡혀있으니까.
             *
             * N+1 문제 해결 : 일단 다 지연로딩으로 사용.
             * 1. fetch join : 런타임에 내가 원하는 애들을 동적으로 선택해 가져옴. 뒤에 나옴.
             * 2. 배치 사이즈
             */
/*지연로딩
            System.out.println("m = " + m.getTeam().getClass()); //team은 프록시로 나옴.
            System.out.println("===================");
            m.getTeam().getName(); //이 때 쿼리문 날림
            System.out.println("===================");
*/

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent); //cascade로 child1, child2도 persist 됨. cascade 없으면 따로 em.persist해줘야함.

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
