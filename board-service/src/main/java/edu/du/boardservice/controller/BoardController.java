package edu.du.boardservice.controller;

import edu.du.boardservice.dto.BoardDto;

import edu.du.boardservice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 공지사항 목록 조회 (검색어 포함)
    @GetMapping
    public Page<BoardDto> getBoardList(
            @RequestParam(name = "keyword", required = false) String keyword,
            Pageable pageable
    ) {
        return boardService.getBoardList(keyword, pageable);
    }

    @GetMapping("/{id}")
    public BoardDto getBoard(@PathVariable("id") Long id) {
        return boardService.getBoard(id);
    }
    // 등록
    @PostMapping
    public BoardDto createBoard(@RequestBody BoardDto dto) {
        return boardService.createBoard(dto);
    }

    // 수정
    @PutMapping("/{id}")
    public BoardDto updateBoard(@PathVariable Long id, @RequestBody BoardDto dto) {
        return boardService.updateBoard(id, dto);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}