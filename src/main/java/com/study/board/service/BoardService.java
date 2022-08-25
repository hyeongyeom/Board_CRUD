package com.study.board.service;


import com.study.board.domain.entity.Board;
import com.study.board.domain.model.BoardDTO;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardService {

    BoardDTO register(BoardDTO boardDTO);
    boolean update(BoardDTO boardDTO);
    List<BoardDTO> boardList();
    BoardDTO detail(Long id);
    boolean delete(Long id);

    boolean deleteAll();

}

