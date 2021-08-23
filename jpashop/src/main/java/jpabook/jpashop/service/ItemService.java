package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); //ID를 기반으로 실제 DB에 있는 영속성 엔티티를 찾아옴.
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        //param으로 넘어온 Book가지고 값을 세팅 했다. -> 스프링의 Transactional에 의해 트랜잭션이 커밋이 된다.
        // -> JPA는 flush를 날림. 영속성 컨테스트에 있는 엔티티 중에 변경된 내용을 찾음. 바뀐 값에 대해 업데이트 쿼리를 날림.
        // 이게 변경 감지. 이렇게 해야 좋음. 다른 방법은 merge

    } //코드 동작 방식이 merge랑 같음.

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
