package org.common.core.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractRequest<T> {
    private T data;
    @JsonFormat(pattern = "yyyyMMdd")
    private Date timestamp;
    public String apiVersion;
}
