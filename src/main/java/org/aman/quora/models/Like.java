package org.aman.quora.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "likes")
public class Like {
    @Id
    private String id;

    private String targetId;
    private String targetType;//QUESTION ANSWER

    private Boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
