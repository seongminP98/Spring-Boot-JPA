package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/*
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
*/

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50) //미리 시퀀스 값을 50개 땡겨옴. 기본값이 50
public class Member {

/*
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //mysql 에서 AUTO_ INCREMENT
    //@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "member_seq_generator") //오라클, PostgreSQL, DB2, H2 에서 사용
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "MEMBER_SEQ_GENERATOR")
*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false) //DB의 컬럼명은 name, not null 제약조건
    private String username;

    public Member(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    /* 필드와 컬럼매핑
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
*/


}

