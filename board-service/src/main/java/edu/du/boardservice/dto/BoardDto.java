
package edu.du.boardservice.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String attachmentUrl;
    private LocalDateTime createdAt;
    private int views;
}
