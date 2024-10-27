package com.liftlab.loadBalancer_v10.service;

import com.liftlab.loadBalancer_v10.dao.ServiceDiscoveryDao;
import com.liftlab.loadBalancer_v10.model.RegisterRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceDiscoveryService {

    @Autowired
    private ServiceDiscoveryDao dao;

    public void register(RegisterRequestBody registerRequestBody){
        dao.register(registerRequestBody);
    }

    public ArrayList<String> getIpAddressList(String serviceId){
        return dao.getIpAddressList(serviceId);
    }

}
