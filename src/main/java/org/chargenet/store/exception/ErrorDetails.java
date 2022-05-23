package org.chargenet.store.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ErrorDetails {

    private String code;
    private String status;
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String path;

    public ErrorDetails(String errorCode, LocalDateTime timestamp, String message, String details, String help) {
        super();
        this.code = errorCode;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.path = help;
    }
}
