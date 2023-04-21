package dev.lanfr0.jwtauth.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// Implementation based on JSend
@JsonInclude(Include.NON_NULL)
public class ResponseMessage<T> {
    private String status; //
    private String message; // Only for error responses
    private T data; // Only for success and fail responses

    // OK and Fail constructor
    private ResponseMessage(String status, T data) {
        this.status = status;
        this.data = data;
    }

    // ERROR constructor
    private ResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>("success", data);
    }

    public static <T> ResponseMessage<T> fail(T data) {
        return new ResponseMessage<>("fail", data);
    }

    public static ResponseMessage<String> error(String message) {
        return new ResponseMessage<>("error", message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
