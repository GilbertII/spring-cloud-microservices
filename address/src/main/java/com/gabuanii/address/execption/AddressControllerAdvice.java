package com.gabuanii.address.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AddressControllerAdvice extends ResponseEntityExceptionHandler {


    @ResponseBody
    @ExceptionHandler(AddressDuplicateException.class)
    protected Map<String, Object> handleAddressDuplicateExecption(AddressDuplicateException e) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", e.getMessage());
        errorMap.put("status", HttpStatus.NOT_FOUND);
        errorMap.put("timestamp", ZonedDateTime.now(Clock.systemDefaultZone()));

        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(AddressNotFoundExecption.class)
    protected Map<String, Object> handleAddressNotFoundExecption(AddressNotFoundExecption e) {

        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", e.getMessage());
        errorMap.put("status", HttpStatus.NOT_FOUND);
        errorMap.put("timestamp", ZonedDateTime.now(Clock.systemDefaultZone()));

        return errorMap;
    }

}
