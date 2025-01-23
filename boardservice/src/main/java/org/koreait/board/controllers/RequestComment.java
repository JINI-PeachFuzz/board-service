package org.koreait.board.controllers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestComment {
    private String mode;
    private Long seq;

    @NotNull // 이런걸 젤 처음에 검증을 해봄
    private Long boardDataSeq;

    @NotBlank
    private String commenter;

    @Size(min=4)
    private String guestPw;

    @NotBlank
    private String content;
}
