package com.study.board.global.exception.board;

import com.study.board.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class BoardException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
    private HttpStatus httpStatus;


    public BoardException(ErrorCode errorCode) {
        this(errorCode,errorCode.getMessage(),errorCode.getHttpStatus());
    }
    public BoardException(ErrorCode errorCode,String customMessage,HttpStatus status) {
        super(customMessage);
        this.errorCode=errorCode;
        this.message=customMessage;
        this.httpStatus=status;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
