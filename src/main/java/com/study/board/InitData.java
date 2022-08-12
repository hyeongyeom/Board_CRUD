package com.study.board;

import com.study.board.domain.entity.Board;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitBoardService initBoardService;

    @PostConstruct
    public void init(){
        initBoardService.init();
    }

    @Component
    static class InitBoardService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            String[] writers = new String[]{"김영진", "이병희", "김준엽", "홍의표", "노영진","최현겸"};
            for (int i = 0; i < 100; i++) {
                Board board = Board.builder().subject("example" + i).contents("test contents")
                    .writer(writers[i % 5])
                    .password("").build();
                em.persist(board);
            }
        }
    }
}
