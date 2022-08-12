package com.study.board.boardService;

import com.study.board.domain.entity.Board;
import com.study.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public  void boardDetail() {
        Optional<Board> board=boardService.detail(1L);
        System.out.println(board);
        if(board.isPresent()) {
            System.out.println(board.get().getSubject());
        }
//    @Test
//    public void regsiterBoard() {
//        UUID uuid=UUID.randomUUID();
//        Board board=new Board(uuid,"Title1","Contents1","최현겸",false,"test123");
//        boardService.register(board);
//        Optional<Board> findBoard=boardService.detail(uuid);
//        if(findBoard != null) {
//            assertThat(board.getSubject()).isEqualTo("Title1");
//        } else {
//            fail();
//        }

    }

}
