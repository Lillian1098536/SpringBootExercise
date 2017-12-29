package edu.university.swagger.model;

import javax.servlet.http.HttpServletResponse;

public enum StudentErrorCode {

    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "E_100", "Student Service error"),
    OK(HttpServletResponse.SC_OK, "E_200", "Student Service OK"),
    NO_STUDENT_FOUND_OK(HttpServletResponse.SC_OK, "E_201", "Student Service Not Found OK"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "E_404", "Student Service Not Found"),
    DATA_ERROR(HttpServletResponse.SC_BAD_REQUEST, "E_405", "Student Service Data Error");

    private final int statusCode;
    private final String localeErrorCode;
    private final String defaultMessage;

    private StudentErrorCode(final int statusCode, final String localeErrorCode, final String defaultMessage) {
        this.statusCode = statusCode;
        this.localeErrorCode = localeErrorCode;
        this.defaultMessage = defaultMessage;
    }

    public static StudentErrorCode getByErrorCode(final String errorCode) {
        for (StudentErrorCode codeEnum : StudentErrorCode.values()) {
            if (codeEnum.localeErrorCode.equalsIgnoreCase(errorCode)) {
                return codeEnum;
            }
        }
        return StudentErrorCode.INTERNAL_SERVER_ERROR;
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
