package com.study.board.controller;

import com.study.board.domain.entity.Board;
import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *  Controller 구현하기
 *  TODO : list, read, create, delete, update 서비스 구현
 *
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/boards")
    public ResponseEntity<List<BoardDTO>> getBoards() {
        return ResponseEntity.ok().body(boardService.boardList());
    }
    @PostMapping("/register")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody Board board) {
        BoardDTO boardDTO=new BoardDTO(board);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(boardService.register(boardDTO));

    }
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.detail(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.delete(id));
    }
}
