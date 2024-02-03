package com.sesac.sesacspring.mapper;

import com.sesac.sesacspring.domain.Board;
import com.sesac.sesacspring.dto.BoardDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> retreiveAll();

    @Insert("insert into board(writer, title, content) values(#{writer}, #{title}, #{content})")
    void createBoard(String writer, String title, String content);

    @Update("updqte board SET title=#{title} content=#{content} where id=#{id}")
    void updateBoard(int id, String title, String content);

    @Delete("delete from board where id = #{id}")
    void deleteBoard(int id);

}
