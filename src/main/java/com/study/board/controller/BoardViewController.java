package com.study.board.controller;

import com.study.board.domain.entity.Board;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardViewController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String boards(Model model) {
        List<Board> boards =boardService.boardList();
        model.addAttribute("boards",boards);
        return "boards";

    }
    @GetMapping("/{id}")
    public  String board(@PathVariable("id") Long id,Model model) {
        Optional<Board> board= boardService.detail(id);
        model.addAttribute("board",board);
        return "board";
    }

//    @GetMapping("/add")
//    public  String
}
