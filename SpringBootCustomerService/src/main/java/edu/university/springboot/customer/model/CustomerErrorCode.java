package edu.university.springboot.customer.model;

import javax.servlet.http.HttpServletResponse;

public enum CustomerErrorCode {

    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "E_100", "Customer Service error"),
    OK(HttpServletResponse.SC_OK, "E_200", "Customer Service OK"),
    NO_STUDENT_FOUND_OK(HttpServletResponse.SC_OK, "E_201", "Customer Service Not Found OK"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "E_404", "Customer Service Not Found"),
    DATA_ERROR(HttpServletResponse.SC_BAD_REQUEST, "E_405", "Customer Service Data Error");

    private final int statusCode;
    private final String localeErrorCode;
    private final String defaultMessage;

    private CustomerErrorCode(int statusCode, String localeErrorCode, String defaultMessage) {
        this.statusCode = statusCode;
        this.localeErrorCode = localeErrorCode;
        this.defaultMessage = defaultMessage;
    }

    public static CustomerErrorCode getByErrorCode(final String errorCode) {
        for (CustomerErrorCode codeEnum : CustomerErrorCode.values()) {
            if (codeEnum.localeErrorCode.equalsIgnoreCase(errorCode)) {
                return codeEnum;
            }
        }
        return CustomerErrorCode.INTERNAL_SERVER_ERROR;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public String getLocaleErrorCode() {
        return localeErrorCode;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
    
}
