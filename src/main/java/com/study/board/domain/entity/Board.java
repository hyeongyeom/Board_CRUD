package com.study.board.domain.entity;

import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
//    @GenericGenerator(name="uuid2",strategy = "org.hibernate.id.UUIDGenerator")
//    @Type(type = "uuid-char")
//    @Column(name = "BOARD_ID", length = 36)
//    private UUID uid;
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
}
