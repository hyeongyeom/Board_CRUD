package com.study.board.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto created ID
    private Long id;
    private String subject;
    @Lob
    private String contents;
    @Column(length = 20)
    private String writer;
    @Column(name = "DELETE_YN",length = 1)
    private boolean isDelete = false;
    private String password;


    public void deleteBoard() {
        isDelete =true;
    }
}
