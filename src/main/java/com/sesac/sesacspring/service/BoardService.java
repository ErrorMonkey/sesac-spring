package com.sesac.sesacspring.service;

import com.sesac.sesacspring.domain.Board;
import com.sesac.sesacspring.dto.BoardDto;
import com.sesac.sesacspring.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDto> retrieveAll() {
        List<Board> boards = boardMapper.retreiveAll();
        List<BoardDto> result = new ArrayList<>();

        for (Board board : boards) {
            BoardDto boardDto = new BoardDto();
            boardDto.setId(board.getId());
            boardDto.setWriter(board.getWriter());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            boardDto.setDate(board.getDate());
            result.add(boardDto);
        }
        return result;
    }

    public void createBoard(
            String writer, String title, String content) {
        boardMapper.createBoard(writer, title, content);
    }

    public void deleteBoard(int id){
        boardMapper.deleteBoard(id);
    }

}
