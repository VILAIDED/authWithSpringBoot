package com.vilaided.AuthTest.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class ResponseCustom<T> {

    private String status;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonIgnore
    private  HttpStatus statusCode;


    public ResponseCustom() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResponseCustom(String status, String message, T data,HttpStatus statusCode) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;

    }


    public ResponseCustom(String status, String message,HttpStatus statusCode) {
        super();
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
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

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
