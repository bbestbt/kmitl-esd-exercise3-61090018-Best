package kmitl.esd.exercise3.customer_service.web;


import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * API for customers (RestController)
 */

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;// Business logic

    /**
     * creating a get mapping that retrieves all the customers detail from the database
     * @return list of customer object as JSON
     */
    @GetMapping("/customer")
    private List<Customer> getAllCustomer(){
        List<Customer> iCustomers =new ArrayList<>();
        customerService.getAllCustomer().forEach(c->iCustomers.add(c));
        return iCustomers;
    }

    //creating a get mapping that retrieves the detail of a specific customer
    @GetMapping("/customer/{id}")
    private Customer getCustomer(@PathVariable("id") Long id){
        return customerService.getCustomerById(id);
    }

    //creating a get mapping that retrieves the detail of a specific customer
    @GetMapping("/customer/resp/{id}")
    private ResponseEntity<Customer> getCustomerResp(@PathVariable("id") Long id){
        Customer customer =customerService.getCustomerById(id);
        return ResponseEntity.ok().header("CustomerName",customer.getName())
                .body(customer);
    }

    //creating a delete mapping that deletes a specific customer
    @DeleteMapping("/customer/{id}")
    private void deleteCustomer(@PathVariable("id") Long id){
        customerService.delete(id);
    }

    /**
     * creating post mapping that post the customer detail in the database
     * via the service (business logic) and the repository (persistence)
     * @param customer JSON object
     * @return the id (unique identifier) for the customer
     */

    @PostMapping("/customer")
    private Long saveCustomer(@RequestBody Customer customer){
        customerService.saveOrUpdate(customer);
        return customer.getId();

    }

}
