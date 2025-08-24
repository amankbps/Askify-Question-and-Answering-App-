package org.aman.quora.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseDTO {
    private String id;
    private String targetId;
    private String targetType;
    private String isLike;
    private LocalDateTime createdAt;
}
