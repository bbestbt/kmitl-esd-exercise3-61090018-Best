
package kmitl.esd.exercise3.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Web service exercise
 * produce endpoints as a part of the server API
 * Controller reacts to requests, controls the workflow, and give a response
 * RESTful webservice -> HTTP method calls.
 */
@RestController
public class HelloController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    /**
     * REST web service endpoint
     * GET (HTTP) method is hello1\
     * Example: http://localhost:8888/hello1?name=Tom
     * @param name a name as a request param (this is optional)
     * @return String with Hello, name
     */
    @GetMapping("/hello1")
    public String hello1Endpoint(@RequestParam(value = "name", defaultValue = "Nobody") String name) {

        return String.format(template, name);
        // return new th.kmitl.esd.exercise-api.Customer(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * REST web service endpoint GET method with name hello2
     * Example : http://localhost:8080/hello2/Mike
     * @param name a name as a path param (is not optional)
     * @return String with Hello, name
     */
    @GetMapping("/hello2/{name}")
    public String test2(@PathVariable("name") String name) {

        return String.format(template, name);
        // return new th.kmitl.esd.exercise-api.Customer(counter.incrementAndGet(), String.format(template, name));
    }
}