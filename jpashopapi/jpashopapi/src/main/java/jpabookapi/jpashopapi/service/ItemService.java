package jpabookapi.jpashopapi.service;

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

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
