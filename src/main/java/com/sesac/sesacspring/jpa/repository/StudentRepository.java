package com.sesac.sesacspring.jpa.repository;

import com.sesac.sesacspring.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// mybatis -> mapper
// jpa(orm) -> repository
// Repository: Entity 에 의해서 만들어진 테이블에 접근하는 메소드들을 정의해 놓은 인터페이스
// CRUD 를 하기 위한 메소드를 정의하는 계층 = Repository
// 기본적으로 JpaRepository 라는 인터페이스를 제공함 -> 전체 조회, 아이디로 조회, 전체 삭제 기능이 포함되어 있음

@Repository
// JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 pk 타입>
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // 1. jpa 의 기본 규칙을 따르는 방법
    // findBy 컬럼명
    // select * from student where 컬럼=조회할 데이터
    // 조회할 데이터와 메소드 명 맞추는 게 좋음
    //
    List<Student> findByName(String name);
    Student findByNameAndNickname(String name, String nickname); // 이름과 닉네임이 모두 일치하는 경우
    Student findByNameOrNickname(String name, String nickname); // 이름과 일치하거나 닉네임이 일치하는 경우
    // findByGreaterThanEqual(int age) // age 값과 같거나 큰 경우

    // 2. 직접 쿼리를 작성해서 연결
//    @Query("select s from Student s where s.name = :a")
    @Query(nativeQuery = true,
            value = "select * from student where name = :a")
    List<Student> findTest(String a);

    Optional<Student> findById(int id);

    //
    int countByNickname(String nickname);

//    @Query(nativeQuery = true, value = "UPDATE studentAll SET name = :name WHERE id = :id")
//    Student updateStudent(int id, String name);
}

