package com.study.board.unit.repository;


import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.NoSuchElementException;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("게시판 저장")
    @Test
    void saveBoard() {
        Board savedBoard = boardRepository.save(getBoard());
        flushAndClear();

        Board actualBoard = boardRepository.findById(savedBoard.getId())
                .orElseThrow(NoSuchElementException::new);

        Assertions.assertThat(actualBoard.getId()).isEqualTo(savedBoard.getId());
    }

    @DisplayName("게시판 삭제")
    @Test
    void deleteBoard() {
        Board savedBoard = boardRepository.save(getBoard());
        savedBoard.deleteBoard();
        flushAndClear();

        Board actualBoard = boardRepository.findById(savedBoard.getId())
                .orElseThrow(NoSuchElementException::new);

        Assertions.assertThat(actualBoard.isDelete()).isEqualTo(savedBoard.isDelete());
    }

    private void flushAndClear() {
        tem.flush();
        tem.clear();
    }

    private Board getBoard() {
        Board board = Board.builder()
                .subject("example")
                .contents("test contents")
                .writer("tester")
                .isDelete(false)
                .password("")
                .build();
        return board;
    }
}