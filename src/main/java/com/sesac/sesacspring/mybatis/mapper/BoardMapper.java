package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> retreiveAll();

    List<Board> getBoardAll();
    // sql 을 작성하거나, xml 파일 작성
    // 1) mapper 에는 메소드가 있고, xml 에는 없는 경우 -> 실행했을 때 오류
    // 2) xml 에는 있고, mapper 에는 없는 경우 -> 실행 자체가 안 된다

    // 내가 짠 거 ㅋㅋ
    @Insert("insert into board(writer, title, content) values(#{writer}, #{title}, #{content})")
    void createBoard(String writer, String title, String content);

    void insertBoard(Board board);

//    @Update("update board SET title=#{title} content=#{content} where id=#{id}")
//    void updateBoard(int id, String title, String content);

    void patchBoard(Board board);

    @Delete("delete from board where id = #{id}")
    void deleteBoard(int id);

    void deleteXmlBoard(int id);

    List<Board> searchBoard(String word);



}
