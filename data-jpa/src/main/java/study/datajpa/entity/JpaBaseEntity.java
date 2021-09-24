package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false) //createdDate는 변경되지 않음.
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    private  void prePersist() { //최초 등록한 데이터
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() { //업데이트 갱신
        updatedDate = LocalDateTime.now();
    }
}
