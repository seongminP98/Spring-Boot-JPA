package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //조인전략. 없으면 JPA 기본이 단일테이블전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//싱클테이블 전략.
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //구현 클래스마다 테이블 전략
@DiscriminatorColumn //테이블에 DTYPE 컬럼 추가됨. 조인전략에서 필요. //싱글테이블 전략에선 없어도 생김.
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

