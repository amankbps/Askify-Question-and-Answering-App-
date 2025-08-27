package org.aman.quora.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCount {
    private String targetId;
    private String targetType;
    private LocalDateTime timestamp;


}
