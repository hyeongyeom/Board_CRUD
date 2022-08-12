package com.study.board.controller;

import com.study.board.domain.entity.Board;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public  String boardDetail(@PathVariable("id") Long id,Model model) {
        Optional<Board> board= boardService.detail(id);
        if(board.isPresent()) {
            Board result=board.get();
            model.addAttribute("board",result);

        }
        return "board";
    }
    @GetMapping("/add")
    public String addBoard(Model model) {
        model.addAttribute("board",new Board());
        return "addForm";
    }
    @PostMapping("/add")
    public  String addBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        System.out.println("this is point");
        System.out.println(board.getId());
        System.out.println(board.getSubject());
        System.out.println(board.getWriter());
        boardService.register(board);
        return "redirect:/board/list";
    }
    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model) {
        Optional<Board> result=boardService.detail(id);
        Board board=result.get();
        model.addAttribute("board",board);
        return "editForm";
    }
    @PostMapping("/edit/{id}")
    public  String edit(@PathVariable("id") Long id,@ModelAttribute Board board) {
        System.out.println(board.getSubject());
        board.setId(id);
        boardService.register(board);
        return "redirect:/board/{id}";
    }

    @GetMapping("/delete/{id}")
        public  String deleteBoard(@PathVariable("id") Long id,Model model) {
            boardService.delete(id);
            return "redirect:/board/list";

    }
//    @GetMapping("/add")
//    public  String
}
