package com.study.board.service;


import com.study.board.domain.entity.Board;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardService {
    boolean register(Board board);
    List<Board> boardList();
    Optional<Board> detail(Long id);
    boolean delete(Long id);

}

