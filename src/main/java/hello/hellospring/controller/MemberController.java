package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    /*@Autowired private final MemberService memberService; => 필드 주입이라고 한다. 뭔가 바꿀 수 있는 방법이 없어서 잘 안쓴다.

    @Autowired // setter 주입이라고 한다. 이건 누군가가 중간에 호출해서 바꿔버리면 문제가 생길 수 있음.
    public setMemberController(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @Autowired // 의존관계 주입 (생성자 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
