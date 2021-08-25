package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false) //DB의 컬럼명은 name, not null 제약조건
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //기본이 EnumType.ORDINAL : enum 순서를 DB에 저장. (사용x)/ STRING은 enum 이름을 DB에 저장.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //과거버전 timestamp, date는 TemporalType.date, time은 TemporalType.time
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP) //과거버전 timestamp
    private Date lastModifiedDate;

    private LocalDate testLocalDate; //최신버전 date
    private LocalDateTime testLocalDateTime; //최신버전 timestamp

    @Lob //DB에 varchar를 넘어서는 큰 컨텐츠를 넣고싶으면 @Lob을 사용.
    private String description;

    @Transient //컬럼에 매핑하지 않음. 메모리에서만 사용. DB에 없음
    private int temp;

    public Member(){

    }
}

