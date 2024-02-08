package com.sesac.sesacspring.repository;

import com.sesac.sesacspring.jpaboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    // title 이 일치 or 검색어가 비어있음
    // 테이블 명이 아니라 Entity 클래스 명을 적어준다
    @Query("select b from BoardEntity b where (b.title=:word or :word= '')")
    List<BoardEntity> searchByWord(String word);

}
