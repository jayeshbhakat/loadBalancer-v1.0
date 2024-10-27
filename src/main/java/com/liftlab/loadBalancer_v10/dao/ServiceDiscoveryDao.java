package com.liftlab.loadBalancer_v10.dao;

import com.liftlab.loadBalancer_v10.Exception.ServiceNotFound;
import com.liftlab.loadBalancer_v10.model.RegisterRequestBody;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class ServiceDiscoveryDao {

    private HashMap<String, ArrayList<String>> map;

    public ServiceDiscoveryDao(){
        ArrayList<String> list = new ArrayList<>();
        list.add("localhost:8082");
        map = new HashMap<>();
        map.put("payments", list);
    }

    public void register(RegisterRequestBody registerRequestBody){
        String serviceId = registerRequestBody.getServiceId();
        String ipAddress = registerRequestBody.getIpAddress();
        if(map.containsKey(serviceId)){
            map.get(serviceId).add(ipAddress);
        }
        else{
            ArrayList<String> list = new ArrayList<>();
            list.add(ipAddress);
            map.put(serviceId, list);
        }
    }

    public ArrayList<String> getIpAddressList(String serviceId){
        if(!map.containsKey(serviceId)) throw new ServiceNotFound("service not found");
        return map.get(serviceId);
    }

}
