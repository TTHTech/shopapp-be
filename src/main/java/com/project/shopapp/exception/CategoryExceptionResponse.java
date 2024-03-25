package com.project.shopapp.exception;

import lombok.Data;

@Data
public class CategoryExceptionResponse {
    private String massage;

    public CategoryExceptionResponse(String massage) {
        this.massage = massage;
    }
}
