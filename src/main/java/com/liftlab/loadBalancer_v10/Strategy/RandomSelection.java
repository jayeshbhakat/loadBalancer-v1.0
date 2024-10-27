package com.liftlab.loadBalancer_v10.Strategy;

import java.util.ArrayList;

public class RandomSelection implements LoadBalancingStrategy{
    @Override
    public String loadBalance(ArrayList<String> ipAddressList) {
        int index = this.getRandomNumber(ipAddressList.size());
        return ipAddressList.get(index);
    }

    public int getRandomNumber(int size){
        return (int) (Math.random() * size);
    }
}
