package com.github.cidarosa.ms.produto.exeptions.handler;

import com.github.cidarosa.ms.produto.exeptions.DataBaseExeption;
import com.github.cidarosa.ms.produto.exeptions.ResourceNotFoundExeption;
import com.github.cidarosa.ms.produto.exeptions.dto.CustomErrorDTO;
import com.github.cidarosa.ms.produto.exeptions.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.crypto.Data;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DataBaseExeption.class)
    public ResponseEntity<CustomErrorDTO> handleDataBase(DataBaseExeption e,
                                                         HttpServletRequest request){

        HttpStatus status = HttpStatus.CONFLICT;

        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }


}
