loadBalancer-v1.0 consists of a service discovery agent and a reverse proxy.


1. Service Discovery Agent
From a microservice client perspective, a microservice must be location transparent, i.e, client must be abstracted away from the physical location of the instances and must locate the instances using a serviced which uniquely identifies a group of instances.
Because the client doesn't the physical location, instances number can be easily scaled and scaled down. Each instance exposes a health check url which can be called to determine the health, if error http status code is returned that means don't route traffic through this instance and restart it.

When an instance is up, the first thing it will do is make a call to the SDA running inside loadBalancer and register itself.
For that in the config we have to pass the ip address of the loadBalancer service and the unique serviced.

ipAddressOfLoadBalancer: "localhost:8081"
spring.application.name = <<unique application name>>


2. Reverse Proxy
It acts as a mediator between client and server. It gives a url to the client to talk to. When url makes a call, it also has to pass the serviced.
Proxy extracts out the serviced from the header and then make a call to the SDA, sda calls redis, gets the list of instances.
Then loadBalacing algorithm based on the config and then it gets back the target microservice ipAddress.
Proxy makes a call to the target instance, gets back the response and then forwards back to the client.

server.port: 8081
spring.application.name: loadBalancer-v1.0
loadBalance.algorithm: "roundRobin"
service.name: "load-balancer"



//// how to start the app ////

first start the loadBalancer-v1.0, application.yaml is already included. Then start the payment-v1.0

send request to localhost:8081/api/v1/load-balancer/api/v1/payments
serviced: <<service_name>> has to be passed in the request header
serviced: payments
