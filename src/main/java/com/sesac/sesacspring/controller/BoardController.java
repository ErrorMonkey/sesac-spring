package com.sesac.sesacspring.controller;

import com.sesac.sesacspring.dto.BoardDto;
import com.sesac.sesacspring.mapper.BoardMapper;
import com.sesac.sesacspring.service.BoardService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/")
    public String getMain(Model model) {
        List<BoardDto> boardList = boardService.retrieveAll();
        model.addAttribute("boardList", boardList);
        return "board";
    }
    @Autowired
    BoardService boardService;

    @GetMapping("/all")
    public List<BoardDto> getBoard() {
        List<BoardDto> result = boardService.retrieveAll();
        return result;
    }

    @PostMapping("/create")
    @ResponseBody
    public void postCreateBoard(@RequestBody BoardDto boardDto) {
//        String writer = boardDto.getWriter();
//        String title = boardDto.getTitle();
//        String content = boardDto.getContent();
//        boardService.createBoard(writer, title, content);
        boardService.createBoard(boardDto.getWriter(), boardDto.getTitle(), boardDto.getContent());
    }

    @PatchMapping("/edit")
    @ResponseBody
    public void patchEditBoard(@RequestBody BoardDto boardDto) {

    }

    @DeleteMapping("/delete")
    public void deleteBoard(@RequestParam int id) {
        boardService.deleteBoard(id);
    }
}
