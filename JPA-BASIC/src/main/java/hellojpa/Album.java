package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //DTYPE이름 바꿀 때 사용. 디폴트는 엔티티이름.(여기선 ALBUM)
public class Album extends Item{

    private String artist;
}
