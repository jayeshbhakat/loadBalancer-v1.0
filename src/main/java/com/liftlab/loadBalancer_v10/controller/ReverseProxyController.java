package com.liftlab.loadBalancer_v10.controller;

import com.liftlab.loadBalancer_v10.service.ReverseProxyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/load-balancer")
public class ReverseProxyController {

    @Autowired
    private ReverseProxyService reverseProxyService;

    @Value("${service.name}")
    private String serviceName;

    @PostMapping("/**")
    public ResponseEntity<Object> routePost(HttpServletRequest request,
                                            @RequestHeader(required = true) String serviceId,
                                        @RequestBody Object requestBody){
        String uri = request.getRequestURI();
        String targetPath = uri.substring(uri.indexOf(this.serviceName) + serviceName.length());
        Object object = reverseProxyService.route(serviceId, targetPath, requestBody);
        return ResponseEntity.accepted().body(object);
    }

//    @GetMapping("/service")
//    public ResponseEntity<Object> routeGet(@RequestHeader(required = true) String serviceId,
//                                        @PathVariable(required = true) String path,
//                                        @RequestBody Object requestBody){
//        Object object = reverseProxyService.route(serviceId, path, requestBody);
//        return ResponseEntity.accepted().body(object);
//    }
//
//    @PatchMapping("/{path}")
//    public ResponseEntity<Object> routePatch(@RequestHeader(required = true) String serviceId,
//                                        @PathVariable(required = true) String path,
//                                        @RequestBody Object requestBody){
//        Object object = reverseProxyService.route(serviceId, path, requestBody);
//        return ResponseEntity.accepted().body(object);
//    }
//
//    @DeleteMapping("/{path}")
//    public ResponseEntity<Object> routeDelete(@RequestHeader(required = true) String serviceId,
//                                        @PathVariable(required = true) String path,
//                                        @RequestBody Object requestBody){
//        Object object = reverseProxyService.route(serviceId, path, requestBody);
//        return ResponseEntity.accepted().body(object);
//    }

}
