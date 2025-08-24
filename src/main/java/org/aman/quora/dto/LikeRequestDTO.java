package org.aman.quora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeRequestDTO {
    @NotBlank(message = "target id is required ")
    private String targetId;
    @NotBlank(message = "Target Type is required ")
    private String targetType;
    @NotBlank(message = " Is Like is required ")
    private String isLike;
}
