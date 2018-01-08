package edu.university.springboot.customer.exception;

import edu.university.springboot.customer.model.CustomerError;
import edu.university.springboot.customer.model.CustomerErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class CustomerExceptionHandler {

    public CustomerError handleEmptyObjectException(HttpServletRequest request, Exception ex) throws Exception {
        log.info("Customer Exception occured URL={} exception={}", request.getRequestURL(), ex.getMessage());
        return this.getResponse(request, CustomerErrorCode.NO_STUDENT_FOUND_OK);
    }

    private CustomerError getResponse(HttpServletRequest request, CustomerErrorCode errorCode) {
        CustomerError response = new CustomerError();
        response.setUrl(request.getRequestURL().toString());
        response.setStatusCode(errorCode.getStatusCode());
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorCode.getLocaleErrorCode() + ": " + errorCode.getDefaultMessage());
        return response;
    }

}
