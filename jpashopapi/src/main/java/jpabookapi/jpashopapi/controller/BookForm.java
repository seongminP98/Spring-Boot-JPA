package jpabookapi.jpashopapi.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BookForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    protected BookForm(){}

    public BookForm(Long id, String name, int price, int stockQuantity, String author, String isbn){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
    public static BookForm createBookForm(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        return new BookForm(id, name, price, stockQuantity, author, isbn);
    }
}
