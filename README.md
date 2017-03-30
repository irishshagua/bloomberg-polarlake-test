# bloomberg-polarlake-test

Original problem was to create a server which proxied calls to some backend server.

The solution has two independant servers acting in a prox fashion. Possible improvments would be to add in a service registry like Eureka or something to avoid having to hardcode service address in configs. Also it'd be good to use a circuit breaker on the gateway side specially if we were gonna be combining several backend calls...


This is a maven build so you can build packages and execute all maven tests etc using mvn clean verify.
There is a docker compose file set up to bring up a light test environment if you wanted to do some ATDD style tests locally.
