package com.study.board.service;

import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public boolean register(Board board) {
        try {
            boardRepository.save(board);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> detail(Long id) {
        return boardRepository.findById(id);


    }

    @Override
    public boolean delete(Long id) {
        try {
            boardRepository.deleteById(id);
        }catch (Exception e) {
            return false;
        }
        return true;

    }
}
