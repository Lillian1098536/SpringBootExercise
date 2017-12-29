package edu.university.swagger.exception;

import edu.university.swagger.model.StudentError;
import edu.university.swagger.model.StudentErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Slf4j
@ResponseBody
@ControllerAdvice
public class StudentExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public StudentError handleSQLException(HttpServletRequest request, Exception ex) throws Exception {
        log.info("SQL exception occured URL={}", request.getRequestURL());
        return this.getResponse(request, StudentErrorCode.DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StudentException.class)
    public StudentError handleJSONException(HttpServletRequest request, Exception ex) throws Exception {
        log.info("StudentException occured URL={}", request.getRequestURL());
        return this.getResponse(request, StudentErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(StudentNotFoundException.class)
    public StudentError handleEmptyObjectException(HttpServletRequest request, Exception ex) throws Exception {
        log.info("StudentNotFoundException URL={}", request.getRequestURL());
        return this.getResponse(request, StudentErrorCode.NO_STUDENT_FOUND_OK);
    }

    private StudentError getResponse(HttpServletRequest request, StudentErrorCode errorCode) {
        StudentError response = new StudentError();
        response.setUrl(request.getRequestURL().toString());
        response.setStatusCode(errorCode.getStatusCode());
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorCode.getLocaleErrorCode() + ": " + errorCode.getDefaultMessage());
        return response;
    }

}
