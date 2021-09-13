package jpabookapi.jpashopapi.domain.item;

import jpabookapi.jpashopapi.domain.Category;
import jpabookapi.jpashopapi.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //싱글테이블 전략
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==// 도메인 주도 설계. 엔티티 자체가 해결할 수 있는건 엔티티 안에 비즈니스 로직을 넣는게 좋음.

    /**
     * stock 증가
     */
    public void addStock(int quantity) { //setter 를 사용해서 변경하는게 아니라 이런걸로 변경하는게 좋다.
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
