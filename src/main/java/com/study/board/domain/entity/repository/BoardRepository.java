package com.study.board.domain.entity.repository;

import com.study.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BoardRepository extends JpaRepository<Board, Long> {


}
