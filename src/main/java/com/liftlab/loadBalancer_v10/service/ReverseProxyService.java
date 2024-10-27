package com.liftlab.loadBalancer_v10.service;

import com.liftlab.loadBalancer_v10.Exception.ApiException;
import com.liftlab.loadBalancer_v10.Exception.ServiceNotFound;
import com.liftlab.loadBalancer_v10.Strategy.StrategyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

@Service
public class ReverseProxyService {

    @Autowired
    private ServiceDiscoveryService serviceDiscoveryService;

    @Autowired
    private StrategyManager strategyManager;

    @Autowired
    private RestTemplate restTemplate;

    public Object route(String serviceId, String path, Object requestBody){
        try{
            ArrayList<String> ipAddressList = serviceDiscoveryService.getIpAddressList(serviceId);
            String targetServiceIpAddress = strategyManager.getLoadBalanceStrategy().loadBalance(ipAddressList);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://" + targetServiceIpAddress + path, requestBody, Object.class);
            return responseEntity.getBody();
        }
        catch(ApiException e){
            throw e;
        }
        catch(RuntimeException e){
            if(e.getMessage().contains("404") || e.getMessage().contains("Connection refused"))
                throw new ServiceNotFound("service not found");
            throw e;
        }
    }
}
