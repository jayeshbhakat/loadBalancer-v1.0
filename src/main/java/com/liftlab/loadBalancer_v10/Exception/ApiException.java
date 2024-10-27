package com.liftlab.loadBalancer_v10.Exception;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
