package com.study.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_REQUEST("REQUEST", "", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("ERROR", "", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND("ENTITY", "해당 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("PASSWORD", "비밀번호가 다릅니다.", HttpStatus.UNAUTHORIZED);

    private String code;
    private String message;
    private HttpStatus httpStatus;
}
