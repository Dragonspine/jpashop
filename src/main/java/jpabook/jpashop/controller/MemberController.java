package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * packageName    : jpabook.jpashop.controller
 * fileName       : MemberController
 * author         : kanghyun Kim
 * date           : 2022/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        kanghyun Kim      최초 생성
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        // MemberForm에서 오류가 있으면 에러페이지(/error)로 가는데,
        // BindingResult 있으면 오류가 담기고 나머지 로직이 실행됨

        // Member가 있어도 MemberForm 만드는게 좋음 > 필요한 데이터가 다르고 Entity가 지저분해짐
        if (result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; //첫페이지로 보냄
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); //Member 대신 MemberDto 쓰는게 더 좋음
        // API는 Entity를 반환하면 절대 안됨 (API스펙이 불안정해짐)
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
