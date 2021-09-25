package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {

//    @Value("#{target.username + ' ' + target.age}") //이게 있으면 Open projection. 엔티티를 다 가져와서 처리
    //없으면 Close Projection. 딱 그것만 가져옴
    String getUsername();
}
