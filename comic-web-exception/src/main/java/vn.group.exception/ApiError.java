package vn.group.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError  {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus httpStatus, String message, List<String> errors) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public ApiError() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
