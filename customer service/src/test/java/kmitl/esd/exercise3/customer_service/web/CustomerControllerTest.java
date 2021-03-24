package kmitl.esd.exercise3.customer_service.web;

import kmitl.esd.exercise3.customer_service.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    private CustomerController controller;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        Customer customer=new Customer(1L,"Tom",36);
        Long id=this.restTemplate.postForObject("http://localhost:"+port+"/customer",
        customer,Long.class);

    }

    /**
     * simple test whether context loads or not
     */
    @Test
    public void contextLoads(){

    }

}