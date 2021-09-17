package jpabookapi.jpashopapi.repository;

import jpabookapi.jpashopapi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //select m from Member m where m.name = ? findByName을 보고 JPA가 이렇게 해준다.
    List<Member> findByName(String name);
}
