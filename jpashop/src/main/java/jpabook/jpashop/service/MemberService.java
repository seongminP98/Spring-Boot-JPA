package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;

import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //JPA의 데이터 변경이나 로직은 트랜잭션 안에서 실행되어야 함. 스프링이 제공하는 @Transactional 사용
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository; //컴파일 시점에 체크 할 수 있기 때문에 final

    /*
//    @Autowired //생성자 주입 //생성자가 하나만 있으면 자동으로 @Autowired 해줌.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @RequiredArgsConstructor 사용하면 됨. final 있는 필드로 자동으로 생성자 만들어줌.
     */

    /**
     * 회원 가입
     */
    @Transactional //default 가 readOnly = false
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //@Transactional(readOnly = true) //조회하는 곳에서 성능 최적화 //읽기가 많기 때문에 클래스 레벨에 해줌. join만 따로 설정.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //한건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
