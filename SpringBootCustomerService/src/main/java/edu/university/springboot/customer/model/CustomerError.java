package edu.university.springboot.customer.model;

import lombok.Data;

@Data
public class CustomerError {

    private int statusCode;
    private String url;
    private CustomerErrorCode errorCode;
    private String errorMessage;

}
