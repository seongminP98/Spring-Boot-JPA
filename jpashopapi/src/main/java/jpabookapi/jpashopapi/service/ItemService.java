package jpabookapi.jpashopapi.service;

import jpabookapi.jpashopapi.domain.item.Book;
import jpabookapi.jpashopapi.domain.item.Item;
import jpabookapi.jpashopapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 그냥 itemRepository 에 단순하게 위임만 하는 클래스이다.
 * 경우에 따라서는 그냥 컨트롤러에서 itemRepository 에 바로 접근해서 사용해도 문제 없다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 변경감지
     * 트랜잭션 안에서 엔티티를 다시 조회, 변경할 값 선택
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); //엔티티조회
//        findItem.change(price, name, stockQuantity); 이런식으로 의미있는 메소드 만들어 사용. set 사용x. 어디서 바뀌는지 역추적 가능.
        findItem.setPrice(price); //엔티티 수정
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);


    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
