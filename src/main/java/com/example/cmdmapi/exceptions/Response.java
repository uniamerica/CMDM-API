package com.example.cmdmapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response {
    private Integer statusHttp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[xxx]")
    private LocalDateTime timestamp;
    private String title;
    private String path;
    private List<Field> fields;

    public Response(Integer statusHttp, LocalDateTime timestamp, String title, String path) {
        this.statusHttp = statusHttp;
        this.timestamp = timestamp;
        this.title = title;
        this.path = path;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Field {
        private String name;
        private String message;
    }
}