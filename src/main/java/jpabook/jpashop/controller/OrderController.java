package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName    : jpabook.jpashop.controller
 * fileName       : OrderController
 * author         : kanghyun Kim
 * date           : 2022/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        kanghyun Kim      최초 생성
 */
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, //param name은 form의 name으로 넘어옴
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        // 컨트롤러에서는 식별자만 넘긴다
        orderService.order(memberId, itemId, count);
        // 서비스의 데이터를 가져오면 Transactional이 없는 컨트롤러에서는 영속상태가 끝남
        return "redirect:/orders";
    }
}
