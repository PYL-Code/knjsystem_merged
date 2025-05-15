// 파일명: BoardServiceImpl.java
package edu.du.boardservice.service;

import edu.du.boardservice.dto.BoardDto;
import edu.du.boardservice.entity.Board;
import edu.du.boardservice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Page<BoardDto> getBoardList(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return boardRepository.findAll(pageable).map(this::toDto);
        } else {
            return boardRepository
                    .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable)
                    .map(this::toDto);
        }
    }

    @Override
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return toDto(board);
    }

    @Override
    public BoardDto createBoard(BoardDto dto) {
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .attachmentUrl(dto.getAttachmentUrl())
                .createdAt(LocalDateTime.now())
                .views(0)
                .build();
        return toDto(boardRepository.save(board));
    }

    @Override
    public BoardDto updateBoard(Long id, BoardDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setAttachmentUrl(dto.getAttachmentUrl());
        return toDto(boardRepository.save(board));
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    private BoardDto toDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .attachmentUrl(board.getAttachmentUrl())
                .createdAt(board.getCreatedAt())
                .views(board.getViews())
                .build();
    }
}