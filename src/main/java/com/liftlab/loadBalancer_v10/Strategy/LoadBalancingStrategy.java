package com.liftlab.loadBalancer_v10.Strategy;

import java.util.ArrayList;

public interface LoadBalancingStrategy {

    public String loadBalance(ArrayList<String> ipAddressList);

}
