package com.sesac.sesacspring.mybatis.controller;

import com.sesac.sesacspring.mybatis.dto.BoardDto;
import com.sesac.sesacspring.mybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/mybatis")
public class BoardController {
    // 5개의 method
    // 1. 전체 조회 -> board.html 렌더링
    @GetMapping("")
    public String getBoard(Model model) {
        List<BoardDto> result = boardService.getBoardAll();
        model.addAttribute("List", result);
        return "board";
    }
    @Autowired
    BoardService boardService;

    @GetMapping("/all")
    public List<BoardDto> getBoard() {
        List<BoardDto> result = boardService.getBoardAll();
        return result;
    }

//    @PostMapping("/create") // /board/mybatis
    @PostMapping("/create") // /board/mybatis
    @ResponseBody // 응답을 주기 위함
//    public void postCreateBoard(@RequestBody BoardDto boardDto) {
    public void insertBoard(@RequestBody BoardDto boardDto) {
        // 2. 게시글 작성
        boardService.insertBoard(boardDto);
        // boardService.createBoard(boardDto.getWriter(), boardDto.getTitle(), boardDto.getContent());
    }

    @PatchMapping("") // /board/mybatis
    @ResponseBody
    // 템플릿 파일을 보여주는데, void 라면 현재 template 을 그대로 다시 보여준다
    public void patchXmlEditBoard(@RequestBody BoardDto boardDto) {
        boardService.patchBoard(boardDto);
    }

    @DeleteMapping("/delete")
    public void deleteBoard(@RequestParam int id) {
        boardService.deleteBoard(id);
    }

    @DeleteMapping("")
    @ResponseBody
    public void deleteXmlBoard(@RequestParam int id) {
        boardService.deleteXmlBoard(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public int searchBoard(@RequestParam String word) {
        return boardService.searchBoard(word);
    }
}
