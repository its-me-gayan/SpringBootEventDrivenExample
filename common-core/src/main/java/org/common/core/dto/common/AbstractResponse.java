package org.common.core.dto.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbstractResponse<T>{
    private T data;
    private boolean isSuccess;
    private Timestamp timestamp;
    public String message;
    public String message_code;
    public String message_description;
    public HttpStatus status;
    public Integer status_code;
}
