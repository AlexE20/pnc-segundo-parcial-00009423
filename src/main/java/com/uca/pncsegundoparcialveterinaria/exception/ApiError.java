package com.uca.pncsegundoparcialveterinaria.exception;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class ApiError {
    private String message;
    private int code;
    private LocalDate timestamp;
    private Object errors;
}
