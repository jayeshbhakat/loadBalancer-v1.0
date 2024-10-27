package com.liftlab.loadBalancer_v10.controller;

import com.liftlab.loadBalancer_v10.model.RegisterRequestBody;
import com.liftlab.loadBalancer_v10.service.ServiceDiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ServiceDiscoveryController {

    @Autowired
    private ServiceDiscoveryService service;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestBody registerRequestBody){
        service.register(registerRequestBody);
    }

}
