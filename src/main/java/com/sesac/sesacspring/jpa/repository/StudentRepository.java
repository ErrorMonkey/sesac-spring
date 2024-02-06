package com.sesac.sesacspring.jpa.repository;

import com.sesac.sesacspring.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// mybatis -> mapper
// jpa(orm) -> repository
// Repository: Entity 에 의해서 만들어진 테이블에 접근하는 메소드들을 정의해 놓은 인터페이스
// CRUD 를 하기 위한 메소드를 정의하는 계층 = Repository
// 기본적으로 JpaRepository 라는 인터페이스를 제공함 -> 전체 조회, 아이디로 조회, 전체 삭제 기능이 포함되어 있음

@Repository
// JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 pk 타입>
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
