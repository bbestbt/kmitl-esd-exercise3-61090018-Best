package kmitl.esd.exercise3.customer_service.service;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.repo.CustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Service layer testing
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Disabled
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void getCustomer() {
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void delete() {
    }
}