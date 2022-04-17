package com.insideinc.jwtapp.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ExceptionResponseDto {

    private String message;
    @JsonInclude(Include.NON_EMPTY)
    private String description;
    @JsonInclude(Include.NON_EMPTY)
    private List<String> stackTrace;
    private LocalDateTime timestamp;

    public ExceptionResponseDto() {
    }

    public ExceptionResponseDto(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
