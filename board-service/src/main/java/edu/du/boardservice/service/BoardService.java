package edu.du.boardservice.service;

import edu.du.boardservice.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface BoardService {
    Page<BoardDto> getBoardList(String keyword, Pageable pageable);
    BoardDto getBoard(Long id);
    BoardDto createBoard(BoardDto dto);
    BoardDto updateBoard(Long id, BoardDto dto);
    void deleteBoard(Long id);
}