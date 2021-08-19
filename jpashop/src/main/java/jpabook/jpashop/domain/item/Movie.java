package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") //싱글테이블에서 구분하기 위해 사용
@Getter @Setter
public class Movie extends Item{

    private String director;
    private String actor;
}
