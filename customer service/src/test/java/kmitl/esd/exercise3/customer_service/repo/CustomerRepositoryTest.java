package kmitl.esd.exercise3.customer_service.repo;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.service.CustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Service layer testing
 */
@RunWith(SpringRunner.class)
@DataJdbcTest
@Disabled

class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private List<Customer> customers = new ArrayList<>();
    @Before
    public void setup() {
        Customer customerJohn = new Customer("Best", 21);
        Customer customerAlex = new Customer( "Orn", 22);

        entityManager.persist(customerJohn);
        entityManager.persist(customerAlex);
        entityManager.flush();

        customers.add(customerJohn);
        customers.add(customerAlex);
    }

    @Test
    void findByNameOrderByName() {
        String name = "Best";
        List<Customer> customers = customerRepository.findByNameOrderByName(name);

        assertEquals(1, customers.size());
        assertEquals(name, customers.get(0).getName());
    }
}