package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    List<Member> findByUsername(String username); //구현하지 않아도 동작함. 쿼리메소드 기능

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy(); //전체 조회.  find...By ...엔 아무거나 들어가도 됨.

    List<Member> findTop3HelloBy(); //limit 3개

//    @Query(name = "Member.findByUsername") //이거 없어도 동작함.
    List<Member> findByUsername(@Param("username") String username); //이름은 상관없음.
    
}
