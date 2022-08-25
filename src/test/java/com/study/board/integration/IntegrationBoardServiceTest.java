package com.study.board.integration;

import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegrationBoardServiceTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityManager em;
    @Autowired
    private BoardService boardService;

    @DisplayName("게시판 생성")
    @Test
    @Transactional
    public void boardDetail() {
        //given
        Board board = getBoard("test");
        em.persist(board);
        //when
        boardRepository.save(board);
        BoardDTO boardDTO = boardService.detail(board.getId());

        //then
        assertThat(boardDTO.getSubject()).isEqualTo(board.getSubject());
    }
    @DisplayName("게시판 리스트 조회")
    @Test
    @Transactional
    public void boardList() {
        //given
        int a=3;
        String[] writers = new String[]{"지수","재영","하나"};
        for (int i = 0; i < a; i++) {
            Board board = Board.builder().subject("example" + i).contents("test contents")
                    .writer(writers[i % a])
                    .password("").build();
            //when
            em.persist(board);
            boardRepository.save(board);
        }
        //then
        List<BoardDTO> boards= boardService.boardList();
        assertThat(boards.size()).isEqualTo(a);

    }
    @DisplayName("게시판 수정")
    @Test
    @Transactional
    public void boardUpdate() {
        //given
        Board board = getBoard("notupdated");
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setContents("updated");
        Board updatedBoard= dto.toEntity(dto);
        boardRepository.save(updatedBoard);
        //then
        assertThat(board.getContents()).isEqualTo("updated");

    }
    @DisplayName("게시판 삭제")
    @Test
    @Transactional
    public void boardDelete() {
        //given
        Board board1= getBoard("test1");
        Board board2= getBoard("test2");
        em.persist(board1);
        em.persist(board2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        //when
        boardService.delete(board2.getId());
        List<BoardDTO> boardDTOS= boardService.boardList();
        //then
        assertThat(boardDTOS.size()).isEqualTo(1);

    }
    @DisplayName("게시판 수정 시 비밀번호 불일치")
    @Test
    @Transactional
    public void updatePasswordFailTest() {
        //given
        Board board= getBoard("notupdated");
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setPassword("wrongPassword");
        dto.setContents("updated");
        Boolean isUpdated= boardService.update(dto);
        //then
        assertThat(isUpdated).isFalse();

    }
    @DisplayName("게시판 수정 시 비밀번호 일치")
    @Test
    @Transactional
    public void updatePasswordSuccessTest() {
        //given
        Board board= getBoard("notupdated");
        //when
        em.persist(board);
        boardRepository.save(board);
        BoardDTO dto= new BoardDTO(board);
        dto.setPassword("password");
        dto.setContents("updated");
        Boolean isUpdated= boardService.update(dto);
        //then
        assertThat(isUpdated).isTrue();
    }

    private Board getBoard(String content) {
        Board board = Board.builder()
                .subject("example")
                .contents(content)
                .writer("tester")
                .isDelete(false)
                .password("")
                .build();
        return board;
    }
}
