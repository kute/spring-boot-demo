package com.kute.demo.exception;

import com.kute.demo.controller.HelloController;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kute on 2018/1/1.
 * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-error-handling
 */
@RestControllerAdvice(basePackageClasses = HelloController.class)
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
//    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);

        System.out.println("error......................");
        ErrorInfo<String > errorInfo = new ErrorInfo<>();
        errorInfo.setCode(status.value());
        errorInfo.setMessage(ex.getMessage());
        errorInfo.setData("extract data");
        errorInfo.setUrl(request.getRequestURL().toString());

        return new ResponseEntity<>(errorInfo, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
