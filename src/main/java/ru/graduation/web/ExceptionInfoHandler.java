package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.graduation.util.ValidationUtil;
import ru.graduation.util.exception.ApplicationException;
import ru.graduation.util.exception.ErrorInfo;
import ru.graduation.util.exception.ErrorType;
import ru.graduation.util.exception.IllegalRequestDataException;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.graduation.util.exception.ErrorType.APP_ERROR;
import static ru.graduation.util.exception.ErrorType.DATA_ERROR;
import static ru.graduation.util.exception.ErrorType.VALIDATION_ERROR;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @Autowired
    private MessageUtil messageUtil;

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
    public static final String EXCEPTION_DUPLICATE_RESTAURANT_NAME = "exception.restaurant.duplicateName";

    private static final Map<String, String> CONSTRAITS_I18N_MAP = Collections.unmodifiableMap(
            new HashMap<String, String>() {
                {
                    put("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL);
                    put("restaurants_unique_name_idx", EXCEPTION_DUPLICATE_RESTAURANT_NAME);
                }
            });

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, ApplicationException appEx) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, appEx, false, appEx.getType(), messageUtil.getMessage(appEx));
        return new ResponseEntity<>(errorInfo, appEx.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.CONFLICT)  //409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();

        if (rootMsg != null) {
            String lowerCaseMsg = rootMsg.toLowerCase();
            Optional<Map.Entry<String, String>> entry = CONSTRAITS_I18N_MAP.entrySet().stream()
                    .filter(it -> lowerCaseMsg.contains(it.getKey()))
                    .findAny();

            if (entry.isPresent()) {
                return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, messageUtil.getMessage(entry.get().getValue()));
            }
        }
        return logAndGetErrorInfo(req, e, true, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) //422
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ErrorInfo bindValidationError(HttpServletRequest req, Exception e) {
        BindingResult result = e instanceof BindException ?
                ((BindException) e).getBindingResult() : ((MethodArgumentNotValidException) e).getBindingResult();

        String[] details = result.getFieldErrors().stream()
                .map(fe -> messageUtil.getMessage(fe))
                .toArray(String[]::new);

        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, details);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY) //422
    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ErrorInfo illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }


    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.getRootCause(e);

        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request {}: {}", errorType, req.getRequestURI(), rootCause.toString());
        }

        return new ErrorInfo(req.getRequestURL(), errorType,
                messageUtil.getMessage(errorType.getErrorCode()),
                details.length != 0 ? details : new String[]{ValidationUtil.getMessage(rootCause)});
    }
}
