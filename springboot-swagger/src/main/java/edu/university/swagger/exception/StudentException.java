package edu.university.swagger.exception;

import edu.university.swagger.model.StudentError;
import edu.university.swagger.model.StudentErrorCode;

public class StudentException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final StudentErrorCode errorCode;
    private String[] messages;

    public StudentException(StudentErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public StudentException(StudentErrorCode errorCode, Throwable e) {
        super(e);
        this.errorCode = errorCode;
        this.messages = messages;
    }

    public StudentException(StudentErrorCode errorCode, String[] messages) {
        super(String.join(",", messages));
        this.errorCode = errorCode;
        this.messages = messages;
    }

    public StudentException(StudentError error) {
        super(error.getErrorMessage());
        this.errorCode = StudentErrorCode.getByErrorCode(error.getErrorCode().toString());
        this.messages = new String[] {error.getErrorMessage()};
    }

    public StudentErrorCode getErrorCode() {
        return errorCode;
    }

    public String[] getMessages() {
        return messages;
    }
}
