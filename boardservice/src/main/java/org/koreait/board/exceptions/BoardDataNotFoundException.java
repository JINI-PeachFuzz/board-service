package org.koreait.board.exceptions;

import org.koreait.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class BoardDataNotFoundException extends CommonException {
    public BoardDataNotFoundException() {
        super("NotFound.boardData", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
// 레스트형태이기 때문에 얼랏빽으로 띄워주는게 없어서 제거후 이렇게 사용함