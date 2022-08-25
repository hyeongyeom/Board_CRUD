package com.study.board.service;

import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import com.study.board.domain.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardDTO register(BoardDTO boardDTO) {
        Board board=boardRepository.save(boardDTO.toEntity(boardDTO));
        return new BoardDTO(board);

    }

    @Override
    public boolean update(BoardDTO boardDTO) {
        Board board=boardDTO.toEntity(boardDTO);
        Board registeredBoard=boardRepository.findById(board.getId()).orElseThrow(() -> new NoSuchElementException());

        if(board.getPassword().equals(registeredBoard.getPassword())) {
            boardRepository.save(board);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BoardDTO> boardList() {

        List<Board> boards =boardRepository.findAll();
        return boards.stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());
    }
    public Long count() {
        return boardRepository.count();
    }
    @Override
    public BoardDTO detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());

        BoardDTO dto = new BoardDTO(board);

        return dto;
    }

    @Override
    public boolean delete(Long id) {
        try {
            boardRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteAll() {
        try {
            boardRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
