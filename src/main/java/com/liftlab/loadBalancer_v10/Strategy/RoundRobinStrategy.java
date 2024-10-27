package com.liftlab.loadBalancer_v10.Strategy;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRobinStrategy implements LoadBalancingStrategy{

    private int counter = 0;
    private ReentrantLock lock = new ReentrantLock();


    @Override
    public String loadBalance(ArrayList<String> ipAddressList) {
        lock.lock();
        try{
            counter = (counter + 1) % ipAddressList.size();
            return ipAddressList.get(counter);
        }
        catch(RuntimeException e){
            throw e;
        }
        finally{
            lock.unlock();
        }
    }
}
