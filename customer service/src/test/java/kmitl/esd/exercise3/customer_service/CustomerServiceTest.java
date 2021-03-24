package kmitl.esd.exercise3.customer_service;

import kmitl.esd.exercise3.customer_service.service.CustomerService;
import kmitl.esd.exercise3.model.CustomerDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test for customer-service
 */
class CustomerServiceTest {

    private CustomerService cs = CustomerService.getInstance();

    /**
     * test to get instance
     */
    @Test
    void getInstance() {
        assertEquals(CustomerService.getInstance(), CustomerService.getInstance());
    }

    /**
     * test to get all customers
     */
    @Test
    void getCustomer() {
        CustomerDTO customerTom = new CustomerDTO(1,"Tom",53);
        cs.createCustomer(customerTom);

        assertEquals(1,cs.getCustomer().size());

    }

    /**
     * test to create customer
     */
    @Test
    void createCustomer() {
        CustomerDTO customerDaniel = new CustomerDTO(1,"Daniel",23);
        cs.createCustomer(customerDaniel);

        assertEquals(1,cs.getCustomer().size());

    }

    /**
     * test to delete customer
     */
    @Test
    void deleteCustomer() {
        CustomerDTO c1 = new CustomerDTO(1, "Bam", 19);
        CustomerDTO c2 = new CustomerDTO(2, "Best", 23);
        CustomerDTO c3 = new CustomerDTO(3, "Bas", 25);

        cs.createCustomer(c1);
        cs.createCustomer(c2);
        cs.createCustomer(c3);

        cs.deleteCustomer(3L);

        List<CustomerDTO> customers = cs.getCustomer();

        assertEquals(2,customers.size());


    }

    /**
     * test to update customer
     */
    @Test
    void updateCustomer() {
        CustomerDTO c = new CustomerDTO(1, "Magic", 20);
        cs.createCustomer(c);
        c.setId(2L);
        CustomerDTO update=cs.updateCustomer(c);

        assertEquals(c.getId(),2L);



    }
}