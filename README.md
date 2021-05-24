# M4R-5H Oil Cleaner

The M4R-5H oil cleaner is a robot designed to handle navigating a patch of sea and clean up any oil patches it traverses
over. The application exposes a REST API for accepting a set of parameters and instructions, and will respond with the
robot's status after simulating the instructions.

## Local Deployment

The service is developed using spring. The application server can be started locally using:

```
mvnw.cmd spring-boot:run   # on windows
./mvnw spring-boot:run     # on linux or macos
```

The application will then be started on the default port `8080`, which must be available. The repository contains a
[`test.http`](test.http) file which can be executed from within IntellIJ in order to quickly verify that the service and
the `/instructions` endpoint are working as expected. 

### Additional Notes

#### Production Readiness

- A core set of features I would consider important for production readiness would be proper CI, release management, and 
  deployment of any artifacts produced by the application. Since I don't have full control over the repository, and I 
  also did not want to edit the provided GitHub actions workflow, I have not implemented this in my solution. You can 
  however find an example of my approach to this in the following repositories:
  
    - https://github.com/FrelliSandbox/some-library
    - https://github.com/FrelliSandbox/some-service
  
  The key highlights are the automated versioning and release management of the repositories, the feature branch and 
  main branch workflows, and the containerization of the `some-service` service.
  