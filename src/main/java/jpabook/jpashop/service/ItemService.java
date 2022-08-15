package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : jpabook.jpashop.service
 * fileName       : ItemService
 * author         : kanghyun Kim
 * date           : 2022/08/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/10        kanghyun Kim      최초 생성
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경감지에 의해서 DB update 하는 방법
    @Transactional
    public void updateItem(Long itemId, int price, String name, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // findOne으로 가져온 Item엔티티는 영속 상태임
        findItem.change(price, name, stockQuantity); // set 대신 change 메서드 새로 만드는게 더 좋음
//        findItem.setPrice(price); // set만 해주면 메서드가 끝날 때 @Transactional에 의해 commit
//        findItem.setName(name);  // commit 하면 JPA가 flush 하는데 이 떄 영속성 컨텍스트의 변경된 부분을 DB에 update 해줌
//        findItem.setStockQuantity(stockQuantity);
//        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
