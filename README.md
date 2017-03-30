# bloomberg-polarlake-test

Original problem was to create a server which proxied calls to some backend server.

The solution has two independant servers acting in a prox fashion. Possible improvments would be to add in a service registry like Eureka or something to avoid having to hardcode service address in configs. Also it'd be good to use a circuit breaker on the gateway side specially if we were gonna be combining several backend calls...
