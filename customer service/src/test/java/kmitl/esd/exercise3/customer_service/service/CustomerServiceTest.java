package kmitl.esd.exercise3.customer_service.service;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.repo.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
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

    private List<Customer> customers;

    @Before
    public void init() {
        Customer c1 = new Customer( "Best", 21);
        Customer c2 = new Customer( "Orn", 22);

        customers = List.of(c1, c2);
    }

    @Test
    void getCustomer() {


        List<Customer> customers = customerService.getAllCustomer();

        assertEquals(2, customers.size());
        assertEquals(21, customers.get(0).getAge());
        assertEquals(19, customers.get(1).getAge());
    }

    @Test
    void getCustomerById() {
        Long id = Long.valueOf(2);


        Customer customer = customerService.getCustomerById(id);

        assertEquals(id, customer.getId());
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void delete() {
    }
}