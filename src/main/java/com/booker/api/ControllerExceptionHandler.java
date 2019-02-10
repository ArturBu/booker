package com.booker.api;

import com.booker.exception.InvalidAppointmentTimeException;
import com.booker.exception.NoFreeStylistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
@Order(HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(final Exception e, final WebRequest request) {
        log.error("Failed to handle request", e);
        return handleExceptionInternal(e, null, null, INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({InvalidAppointmentTimeException.class})
    public ResponseEntity<Object> handleInvalidInvalidAppointmentTimeException(final Exception e, final WebRequest request) {
        log.error("Failed to handle request! Wrong appointmentTime", e);
        return new ResponseEntity("Wrong appointment time", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoFreeStylistsException.class})
    public ResponseEntity<Object> handleNoFreeStylistsException(final Exception e, final WebRequest request) {
        log.error("Failed to handle request! Wrong appointmentTime", e);
        return new ResponseEntity("Sorry but there is no more free stylist at this time :(((", HttpStatus.BAD_REQUEST);
    }
}
