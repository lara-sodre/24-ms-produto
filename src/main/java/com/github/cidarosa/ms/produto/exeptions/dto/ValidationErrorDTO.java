package com.github.cidarosa.ms.produto.exeptions.dto;

import lombok.Getter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDTO extends CustomErrorDTO{

    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(Instant timestrap, Integer status, String error, String path){

        super(timestrap, status, error, path);
    }


    public void addError(String fieldName, String message){

        errors.removeIf(x -> x.getFieldName().equals(fieldName));
        errors.add(new FieldMessageDTO(fieldName, message));
    }
}
