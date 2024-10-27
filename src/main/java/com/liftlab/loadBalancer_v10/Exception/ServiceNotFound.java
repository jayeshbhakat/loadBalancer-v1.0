package com.liftlab.loadBalancer_v10.Exception;

public class ServiceNotFound extends ApiException {
    public ServiceNotFound(String message){
        super(message);
    }
}
