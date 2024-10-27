package com.liftlab.loadBalancer_v10.Strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StrategyManager {

    @Value("${loadBalance.algorithm}")
    private String algorithm;

    public LoadBalancingStrategy getLoadBalanceStrategy(){
        LoadBalancingStrategy result = new RoundRobinStrategy();
        switch(algorithm){
            case "roundRobin":
                result =  new RoundRobinStrategy();
                break;

            case "random":
                result =  new RandomSelection();
                break;
        }
        return result;
    }
}
