package com.sesac.sesacspring.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Entity : 데이터베이스의 필드와 변수의 연관관계가 정의된
 // : DB 테이블에 대응되는 하나의 클래스
@Entity // class Student() {} // 빈 생성자가 필수로 필요하다
@NoArgsConstructor
@Builder // 필드 전체가 들어있는 생성자가 필수로 필요하다
@AllArgsConstructor
@Getter
@Table(name="studentAll") // 강제로 이름 지정
public class Student {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 의미
    private int id;
    // id int primary key auto_increment
    // SEQUENCE : 새로운 오브젝트를 만들어서 id를 부여하는 방법 ( mysql x )
    // TABLE : SEQUENCE 전략을 흉내낸 전략 -> 모든 DBMS 에서 사용 가능

    @Column(name= "name", nullable = false, length = 20)
    private String name;
    // name varchar(20) not null,
    @Column(columnDefinition = "TEXT")
    private String nickname;

    // enum
    @Enumerated(EnumType.STRING)
    private LoginType type;
    public enum LoginType {
        GOOGLE, KAKAO
    }

}
