# MicroService:
    -   config-server: for configurations
    -   eureka-naming-server: for registering of all microservices
    -   api-gateway: for api-gateway: modify request header/params/authenticate etc:
        -   bestpractice:  pass all request through api-gateway for logging,auth,security etc
        -   resilence4j: for  rate-limiter, retry, circuit-breaker,bulkhead-concurrent calls
    -   feign: for proxy controler to talk to different micro-service
    -   netflix-eureka-client: to register to eureka server
    -   load-balancer: through the eureka-server


# Troubleshoot

    - : Issue:   
        -   config-server port should be changed from default 8888 to anything else
        -   because when the config-server port it default 8888, 
            and we try to fetch http://localhost:8888/{application}/{profile}
            -   it tries to  fetch from http://localhost:8888/{application}/{profile} again and again
            -   because this url is available in {application}.properties in client-service
                so there is a recursive calls to this endpoint.
            -   if the default port not changed it takes 3 minutes and 5-10 sec for the response
                -   also the client-service not able to load the config from the config-server
                -   because of timeout
            
            -   if default port changed it takes very less time
    -   :   Solution:
        -   Don't include spring.config.import=optional:configserver:http://localhost:8888/ property
            in git-config-server {application}.properties file, because it will try fetching config server recursive and will end up in a infinite loop  
