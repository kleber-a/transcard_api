package com.kleber.transcard.infra;

import com.kleber.transcard.exceptions.BaseException;
import com.kleber.transcard.exceptions.common.CommonMethodNotAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<RestErrorMessage> handleBaseException(BaseException ex) {
        HttpStatus status = switch (ex.getClass().getSimpleName()) {
            case "UserNotFoundException", "CardNotFoundException" -> HttpStatus.NOT_FOUND;
            case "InvalidCredentialsException" -> HttpStatus.UNAUTHORIZED;
            case "UnauthorizedException" -> HttpStatus.FORBIDDEN;           // 403
            case "EmailAlreadyExistsException", "CardAlreadyExistsException" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_REQUEST;
        };

        return ResponseEntity.status(status)
                .body(new RestErrorMessage(status, ex.getMessage()));
    }

//    @ExceptionHandler(CommonMethodNotAllowed.class) // fallback
//    public ResponseEntity<RestErrorMessage> handleGeneric(CommonMethodNotAllowed ex) {
//        return ResponseEntity
//                .status(HttpStatus.METHOD_NOT_ALLOWED)
//                .body(new RestErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()));
//    }


    // Fallback para qualquer outro erro inesperado → 500
    @ExceptionHandler(Exception.class) // fallback
    public ResponseEntity<RestErrorMessage> handleGeneric(Exception ex) {
//        logger.error("Erro não tratado", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor"));
    }
}

