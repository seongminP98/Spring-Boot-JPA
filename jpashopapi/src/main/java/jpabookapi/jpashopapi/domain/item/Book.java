package jpabookapi.jpashopapi.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter(AccessLevel.PROTECTED)
public class Book extends Item{

    private String author;
    private String isbn;

    public static Book createBook(String author, String isbn, String name, int stockQuantity, int price) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);

        return book;
    }

    public static Book editBook(Long id, String author, String isbn, String name, int stockQuantity, int price) {
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);

        return book;
    }
}
