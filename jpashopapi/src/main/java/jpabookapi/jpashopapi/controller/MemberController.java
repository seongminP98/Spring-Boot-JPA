package jpabookapi.jpashopapi.controller;

import jpabookapi.jpashopapi.domain.Address;
import jpabookapi.jpashopapi.domain.Member;
import jpabookapi.jpashopapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createFrom(Model model) {
        model.addAttribute("memberForm", new MemberForm()); //빈 껍데기 멤버 객체. validation 같은걸 해줌.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    /**
     * 단순해서 member 객체를 뿌렸지만, DTO를 이용해서 필요한 것만 뿌리는게 좋다.
     * API를 만들때는 절때 엔티티를 외부에 반환하면 안된다.
     */
}
