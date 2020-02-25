package vn.group.exception;

import com.google.protobuf.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<Object> springHandlerNotFound(NotFoundObjectException e) throws IOException {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        List<String>   errors  = new ArrayList<String>();
        errors.add("404");
        apiError.setErrors(errors);
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }
    @ExceptionHandler(ExecDatabaseException.class)
    public ResponseEntity<Object> srpingHandlerExecDatabase(ExecDatabaseException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        List<String> errors = new ArrayList<String>();
        errors.add(e.getMessage());
        apiError.setErrors(errors);
        apiError.setMessage("An error occurred");
        apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }
}
