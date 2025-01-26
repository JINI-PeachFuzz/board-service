package org.koreait.board.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.board.entities.Board;
import org.koreait.board.services.configs.BoardConfigDeleteService;
import org.koreait.board.services.configs.BoardConfigInfoService;
import org.koreait.board.services.configs.BoardConfigUpdateService;
import org.koreait.board.validators.BoardConfigValidator;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.global.paging.ListData;
import org.koreait.global.rests.JSONData;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 일반컨트롤러는 사용할 일이 없음!
// rest(JSON, XML)로 주고 받을 거니까 / 웹서비스 컨트롤러 / 컨트롤러랑 리스폰스바디결합형
@RequestMapping("/admin") // 우리가 정한 경로! / 관리자쪽
@RequiredArgsConstructor // 생성자 자동생성해주는거 / 의존성 주입
public class BoardAdminController {

    private final Utils utils;
    private final BoardConfigValidator configValidator;
    private final BoardConfigUpdateService updateService;
    private final BoardConfigInfoService infoService;
    private final BoardConfigDeleteService deleteService;

    /**
     * 게시판 설정 등록, 수정 처리
     * // 등록, 수정을 하고나면 결과를 바로바로 보고싶어함
     * 그래서 반환값으로 수정했던걸 보여주게 설정을 넣어줌 그래서 JSONData를 넣어줌
     * @return
     */
    @PostMapping("/config")
    public JSONData save(@Valid @RequestBody RequestConfig form, Errors errors) {

        configValidator.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        Board board = updateService.process(form);

        return new JSONData(board);
    }

    /**
     * 게시판 설정 목록
     *
     * @return
     */
    @GetMapping("/config")
    public JSONData list(@ModelAttribute BoardConfigSearch search) {

        ListData<Board> items = infoService.getList(search);

        return new JSONData(items);
    }

    /**
     * 게시판 한개 또는 여러개 일괄 수정
     * // put은 전체를 대체하는반면 patch는 일부만 수정할 때 사용
     * @return
     */
    @PatchMapping("/config")
    public JSONData update(@RequestBody List<RequestConfig> form) {

        List<Board> items = updateService.process(form);

        return new JSONData(items);
    }

    /**
     * 게시판 한개 또는 여러개 삭제 처리
     * // 삭제할때도 후속처리가 있을꺼임 / 그래서 변경된걸 보여주는걸로
     * @param bids
     * @return
     */
    @DeleteMapping("/config")
    public JSONData delete(@RequestParam("bid") List<String> bids) {

        List<Board> items = deleteService.process(bids);

        return new JSONData(items);
    }
}
