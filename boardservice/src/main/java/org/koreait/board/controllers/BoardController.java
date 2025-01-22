package org.koreait.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.board.validators.BoardValidator;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.global.rests.JSONData;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final Utils utils;
    private final BoardValidator boardValidator;

    /**
     * 게시판 설정 한개 조회
     *
     * @param bid
     * @return
     */
    @GetMapping("/config/{bid}") // 경로변수 추가
    public JSONData config(@PathVariable("bid") String bid) { // URL 경로에서 값을 추출하기 위한 애노테이션
    // /config/123일경우 bid는 123이 되는거 // 겟방식이라 123을 전달해줌 // id는 유일무이한 거임
        // {bid} 이부분은 중괄호로 감싸야하고, 경로변수와 PathVariable에 적은거==bid 2개는 동일해야함
        return null;
    }

    /**
     * 게시글 등록, 수정 처리
     *
     * @return
     */
    @PostMapping("/save") // 한꺼번에 조회하기 위해 post방식으로 했음
    public JSONData save(@Valid @RequestBody RequestBoard form, Errors errors) {
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode : "write";
        commonProcess(form.getBid(), mode); // 공통 처리

        boardValidator.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }


        return null;
    }

    /**
     * 게시글 한개 조회
     * - 글보기, 글 수정시에 활용될 수 있음(프론트앤드)
     *
     * @param seq
     * @return
     */
    @GetMapping("/view/{seq}")
    public JSONData view(@PathVariable("seq") Long seq) {
        commonProcess(seq, "view"); // Long seq 경로변수


        return null;
    }

    /**
     * 게시글 목록 조회
     *
     * @param bid
     * @return
     */
    @GetMapping("/list/{bid}")
    public JSONData list(@PathVariable("bid") String bid) {
        commonProcess(bid, "list");

        return null;
    }

    /**
     * 게시글 한개 삭제
     *
     * @param seq
     * @return
     */
    @DeleteMapping("/{seq}")
    public JSONData delete(@PathVariable("seq") Long seq) {
        commonProcess(seq, "delete");

        return null;
    }


    /**
     * 게시글 번호로 공통 처리
     *
     * @param seq
     * @param mode
     */
    private void commonProcess(Long seq, String mode) {

    }

    /**
     * 게시판 아이디로 공통 처리
     *
     * @param bid
     * @param mode
     */
    private void commonProcess(String bid, String mode) {

    }
}
