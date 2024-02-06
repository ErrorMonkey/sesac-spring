package com.sesac.sesacspring.mybatis.service;

import com.sesac.sesacspring.mybatis.domain.Board;
import com.sesac.sesacspring.mybatis.dto.BoardDto;
import com.sesac.sesacspring.mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    // 의존성 주입
    @Autowired
    BoardMapper boardMapper;
    //    public List<BoardDto> retrieveAll() {
//        List<Board> boards = boardMapper.retreiveAll();
    public List<BoardDto> getBoardAll() {
        List<Board> result = boardMapper.getBoardAll();
        List<BoardDto> boards = new ArrayList<>();

        for (Board board : result) {
            BoardDto boardDto = new BoardDto();
            boardDto.setBoardID(board.getId());
            boardDto.setWriter(board.getWriter());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            boardDto.setNo(100 + board.getId());
            boardDto.setDate(board.getDate());
            boardDto.setRegistered(board.getRegistered());
            boards.add(boardDto);
        }
        return boards;
    }

    // 내가 짠 거 ㅋㅋ
    public void createBoard(
            String writer, String title, String content) {
        boardMapper.createBoard(writer, title, content);
    }

    public boolean insertBoard(BoardDto boardDto) {
       Board board = new Board();
        board.setTitle(boardDto.getTitle());
       board.setContent(boardDto.getContent());
       board.setWriter(boardDto.getWriter());

       boardMapper.insertBoard(board);
       return true;
    }

    public void patchBoard(BoardDto boardDto) {
        // board.getBoardID // title, content, writer
        Board board = new Board();
        board.setId(boardDto.getBoardID());
        board.setWriter(boardDto.getWriter());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setDate(boardDto.getDate());
        boardMapper.patchBoard(board);
    }

    public void deleteBoard(int id){
        boardMapper.deleteBoard(id);
    }

    public void deleteXmlBoard(int id) {
        boardMapper.deleteXmlBoard(id);
    }

    public int searchBoard(String word) {
        List<Board> result = boardMapper.searchBoard(word);
        return result.size();
    }
}
