package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * packageName    : jpabook.jpashop.repository
 * fileName       : ItemRepository
 * author         : kanghyun Kim
 * date           : 2022/08/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/10        kanghyun Kim      최초 생성
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { // 처음 저장할땐 아이디가 없다(신규등록)
            em.persist(item);
        } else { // 아이디가 있으면 update비슷하게 처리
            em.merge(item); // 실무에서 거의 쓸일이 없다?
            // 변경감지 기능을 사용하면 원하는 속성만 update가능하지만
            // merge 쓰면 모든 속성이 update되버림 > 값없으면 null로 업데이트됨
            // 결론 > update시 ItemRepository.save 말고 ItemService.updateItem 써라
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
