package com.liftlab.loadBalancer_v10.service;

import com.liftlab.loadBalancer_v10.Strategy.RoundRobinStrategy;
import com.liftlab.loadBalancer_v10.Strategy.StrategyManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReverseProxyServiceTest {

    @InjectMocks
    private ReverseProxyService testClass;

    @Mock
    private ServiceDiscoveryService serviceDiscoveryService;

    @Mock
    private StrategyManager strategyManager;

    @Mock
    private RoundRobinStrategy roundRobinStrategy;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testRoute(){
        assertEquals("1", "1");
        ArrayList<String> ipAddressList = new ArrayList<>();
        ipAddressList.add("localhost:8082");
        ipAddressList.add("localhost:8083");
        ResponseEntity responseEntity = ResponseEntity.status(200).body(new Object());
        when(serviceDiscoveryService.getIpAddressList(any()))
                .thenReturn(ipAddressList);
        when(strategyManager.getLoadBalanceStrategy())
                .thenReturn(roundRobinStrategy);
        when(roundRobinStrategy.loadBalance(any(ArrayList.class)))
                .thenReturn(
                        "localhost:8083"
                );
        when(restTemplate.postForEntity(eq("http://localhost:8083/api/v1/payments"),
                any(Object.class),
                eq(Object.class)))
                .thenReturn(responseEntity);
        Object object = testClass.route("payments", "/api/v1/payments", new Object());
        assertNotNull(object);
    }

}