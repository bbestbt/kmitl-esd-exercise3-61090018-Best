package kmitl.esd.exercise3.server;

import kmitl.esd.exercise3.model.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test for customer controller
 */
class CustomerControllerTest {
    private CustomerController customerController = new CustomerController();


    /**
     * test to get all customer
     */
    @Test
    void getAllCustomers() {
        CustomerDTO c1=new CustomerDTO(1,"han so jun",20);
        CustomerDTO c2=new CustomerDTO(2,"lee su ho",20);
        customerController.createCustomer(c1);
        customerController.createCustomer(c2);
        assertEquals(customerController.getAllCustomers().size(),4);

    }

    /**
     * test to create customer
     */
    @Test
    void createCustomer() {
        CustomerDTO c1=new CustomerDTO(1,"han so jun",20);
        customerController.createCustomer(c1);
        assertEquals(customerController.getAllCustomers().size(),3);

    }

    /**
     * test to update customer
     */
    @Test
    void updateCustomer() {
        CustomerDTO c1=new CustomerDTO(1,"han so jun",20);
        customerController.createCustomer(c1);
        c1.setId(10L);
        CustomerDTO update=customerController.updateCustomer(c1);
        assertEquals(update.getId(),10L);
    }

    /**
     * test to delete customer
     */
    @Test
    void deleteCustomer() {
        CustomerDTO c1=new CustomerDTO(1,"han so jun",20);
        CustomerDTO c2=new CustomerDTO(2,"lee su ho",20);
        customerController.createCustomer(c1);
        customerController.createCustomer(c2);
        customerController.deleteCustomer(1L);
        assertEquals(customerController.getAllCustomers().size(),3);

    }
}