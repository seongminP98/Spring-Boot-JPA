package jpabookapi.jpashopapi.controller;

import jpabookapi.jpashopapi.domain.item.Book;
import jpabookapi.jpashopapi.domain.item.Item;
import jpabookapi.jpashopapi.repository.ItemRepository;
import jpabookapi.jpashopapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        Book book = Book.createBook(form.getAuthor(), form.getIsbn(), form.getName(), form.getStockQuantity(), form.getPrice());
/*
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
*/

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = BookForm.createBookForm(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), item.getAuthor(), item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {

//        Book book = Book.editBook(form.getId(), form.getAuthor(), form.getIsbn(), form.getName(), form.getStockQuantity(), form.getPrice());

        //엔티티를 파라미터로 사용하지말자. 업데이트 할 데이터가 많으면 => 서비스 계층에 DTO를 만들자.
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
    /**
     * book은 id가 세팅이 되어있음.
     * 준영속 상태의 객체. JPA가 식별할 수 있는 id를 가지고 있기 때문.
     * 준영속 엔티티? =>영속성 컨텍스트가 더는 관리하지 않는 엔티티
     * 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준영속 엔티티로 볼 수 있다.
     */

}
