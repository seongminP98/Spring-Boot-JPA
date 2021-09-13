package jpabookapi.jpashopapi.domain;

import jpabookapi.jpashopapi.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",//다대다는 조인테이블 필요(중간 테이블. 1대다 다대1로 풀어내는)
            joinColumns = @JoinColumn(name = "category_id"), //중간테이블의 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id") //Item 쪽으로 들어가는.
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 편의 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
    
}
