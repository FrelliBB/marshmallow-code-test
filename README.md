# M4R-5H Oil Cleaner

| [API Documentation](http://localhost:8080/swagger-ui.html) | [Healthcheck](http://localhost:8081/actuator/health)
| [JMX](http://localhost:8081/actuator/hawtio) | [Metrics](http://localhost:8081/actuator/prometheus) |

[comment]: <> (I would normally link to a staging or production environment for the above URLs rather than the 
localhost version.)

The M4R-5H oil cleaner is a robot designed to handle navigating a patch of sea and clean up any oil patches it traverses
over. The application exposes a REST API for accepting a set of parameters and instructions, and will respond with the
robot's status after simulating the instructions.

## Local Deployment

The service is developed using spring. The application server can be started locally using:

```
mvnw.cmd spring-boot:run   # on windows
./mvnw spring-boot:run     # on linux or macos
```

The application will then be started on ports `8080` and `8081` for the the application and management endpoints
respectively. The repository contains a [`test.http`](test.http) file which can be executed from within IntellIJ in
order to quickly verify that the service and the `/instructions` endpoint are working as expected.

## Architecture

### Domain

The domain model for the application is split up into three separate and distinct components. This allows the code to be
simple to read through, as well as making the testing of each individual component a lot simpler, rather than testing
all possible edge cases at once. The three components are:

- `OilCleaner` Keeps track of the oil positions, and the counter for how many oil patches have been cleaned.
- `SeaArea` Defines the size of the grid and exposes logic for validating co-ordinate positions in the grid.
- `Robot` Accepts `Direction` instructions and ties the above two together to orchestrate navigation. Can generate the
  status report of the process.

### Immutability

All domain objects are immutable and thus cannot be changed once initialized. Any methods that would change the state of
the object will instead return a new instance.

The main benefit of this immutability is thread-safety. We could, in theory, have a single `Robot` instance that can
process hundreds of different instructions sets on separate threads, and it will not result in any corrupt results. The
trade-off is that the application might end up using more memory, so that is something that must be kept in mind.

Another benefit, perhaps a bit subjective, is that immutable objects result in cleaner code since it becomes impossible
to write methods that have side effects on the object you're referencing. Debugging becomes a lot easier due to this
since you know that the state you initialize an object in will be its state forever.

## Notes

### Additional Production Readiness Features

- A core set of features I would consider important for production readiness would be proper CI, release management, and
  deployment of any artifacts produced by the application. Since I don't have full control over the repository, and I
  also did not want to edit the provided GitHub actions workflow, I have not implemented this in my solution. You can
  however find an example of my approach to this in the following repositories:

    - https://github.com/FrelliSandbox/some-library
    - https://github.com/FrelliSandbox/some-service

  The key highlights are the automated versioning and release management of the repositories, the feature branch and
  main branch workflows, and the containerization of the `some-service` service.

- Metrics and logs are a core part of an application being production ready. The application exposes default metrics for
  Prometheus to scrape. In a production scenario there would need to be an agent that collects these metrics and a
  visualisation tool such as Grafana to display them.

- Management endpoints are exposed (via Spring Actuator) on port `8081`. In a production scenario we would want to make
  sure that these endpoints are not publicly accessible, either via the application level security with Spring Security,
  or at the network level using a firewall (or both).

---

I hope you find this implementation satisfactory, and I'm looking forward to hearing from you.

_Francesco_