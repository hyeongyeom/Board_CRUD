package com.study.board.unit.service;

import com.study.board.domain.entity.Board;
import com.study.board.domain.entity.repository.BoardRepository;
import com.study.board.domain.model.BoardDTO;
import com.study.board.service.BoardService;
import com.study.board.service.BoardServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardRepository boardRepository;


    @DisplayName("mock 객체 생성 확인")
    @Test
    void mockNotNullTest(){
        assertThat(boardService).isNotNull();
    }

    @DisplayName("게시판 등록")
    @Test
    void saveBoard() {
        Board board=getBoard();
        BoardDTO dto=new BoardDTO(board);

        given(boardRepository.save(any(Board.class)))
                .willReturn(board);

        BoardDTO savedBoardDto=boardService.register(dto);

        assertThat(savedBoardDto.getId()).isNotNull();
        verify(boardRepository,times(1)).save((any(Board.class)));

    }
    @DisplayName("게시판 삭제")
    @Test
    void deleteBoard() {
        Board board = getBoard();

        doNothing().when(boardRepository).deleteById(anyLong());

        boardService.delete(1L);

        verify(boardRepository, times(1))
                .deleteById(anyLong());
    }



    private Board getBoard() {
        Board board = Board.builder()
                .id(1L)
                .subject("example")
                .contents("test contents")
                .writer("tester")
                .isDelete(false)
                .password("")
                .build();
        return board;
    }
}
