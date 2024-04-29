package dev.alair.fresh.advice;

import dev.alair.fresh.dtos.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullException(NullPointerException e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Something went wrong! Global");

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }
}
