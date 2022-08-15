package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * packageName    : jpabook.jpashop.controller
 * fileName       : ItemController
 * author         : kanghyun Kim
 * date           : 2022/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/14        kanghyun Kim      최초 생성
 */
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        // setter 보단 생성자로 생성하는게 더 좋은 설계임
        // 실무에선 @setter 안쓰는게
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> itemList = itemService.findItems();
        model.addAttribute("items", itemList);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, BookForm form) {
//        // 이미 DB에 한번 저장되어 식별자가 존재하면 준영속 엔티티로 볼 수 있다
//        // 준영속 엔티티는 itemService.saveItem 엑션이 없으면 DB에 업데이트가 되지 않는다.
//        Book book = new Book(); // new로 생성한 엔티티는 set을 해도 DB업데이트가 안됨
//                                // (itemRepository.findOne 처럼 가져와야 영속성을 가짐)
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        itemService.saveItem(book); // itemRepository.save 시 em.merge 가 호출됨
//        // merge호출시 파라미터로 넘어온 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회
//        // 캐시에 없으면 DB에서 조회하여 1차 캐시에 저장
//        // 조회한 영속 엔티티를 파라미터의 준영속 엔티티 값으로 다 바꿔치기 해서 update함

        // BookForm은 웹계층에서 쓰는 데이터라 itemService로 BookForm을 가져가면 지저분해짐,
        // 필요한 파라미터만 가져가서 쓰는 아래 로직이 더 나음
        // 파라미터 너무 많으면 UpdateItemDto 같은거 만들어서 쓰자
        itemService.updateItem(itemId, form.getPrice(), form.getName(), form.getStockQuantity());
        return "redirect:/items";
    }
}
