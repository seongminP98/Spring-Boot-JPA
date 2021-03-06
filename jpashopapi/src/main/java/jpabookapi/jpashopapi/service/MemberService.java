package jpabookapi.jpashopapi.service;

import jpabookapi.jpashopapi.domain.Member;
import jpabookapi.jpashopapi.repository.MemberRepository;
import jpabookapi.jpashopapi.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)   //JPA의 데이터 변경은 트랜잭션 안에서
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional //쓰기를 하니까 readOnly를 false로 하기 위해. 디폴트가 false
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
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //id로 조회
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name); //변경감지
    }
}
