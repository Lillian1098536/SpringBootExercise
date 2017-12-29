package edu.university.swagger.model;

import lombok.Data;

@Data
public class StudentError {

    private int statusCode;
    private String url;
    private StudentErrorCode errorCode;
    private String errorMessage;

}
