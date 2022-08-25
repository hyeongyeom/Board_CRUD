package com.study.board.controller;

import com.study.board.domain.entity.Board;
import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardViewController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String boards(Model model) {
        List<BoardDTO> boardDTOS =boardService.boardList();
        model.addAttribute("boards",boardDTOS);
        return "boards";

    }
    @GetMapping("/{id}")
    public  String boardDetail(@PathVariable("id") Long id,Model model) {
        BoardDTO boardDTOS= boardService.detail(id);
        model.addAttribute("board",boardDTOS);
        return "board";
    }
    @GetMapping("/add")
    public String addBoard(Model model) {
        model.addAttribute("board",new BoardDTO());
        return "addForm";
    }
    @PostMapping("/add")
    public  String addBoard(@ModelAttribute BoardDTO board, RedirectAttributes redirectAttributes) {
        boardService.register(board);
        return "redirect:/board/list";
    }
    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTOS = boardService.detail(id);
        model.addAttribute("board",boardDTOS);
        return "editForm";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute BoardDTO board, Model model,RedirectAttributes redirectAttributes) {
        board.setId(id);
        try {
           boolean result= boardService.update(board);

           if(!result) {
               redirectAttributes.addFlashAttribute("passwordError",true);
               return "redirect:/board/edit/{id}";
           }

        }catch(NoSuchElementException e) {

        }
        return "redirect:/board/{id}";
    }

    @GetMapping("/delete/{id}")
        public  String deleteBoard(@PathVariable("id") Long id,Model model) {
            boardService.delete(id);
            return "redirect:/board/list";

    }
}
