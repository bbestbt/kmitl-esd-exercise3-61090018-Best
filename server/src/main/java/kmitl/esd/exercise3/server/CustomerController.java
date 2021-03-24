
package kmitl.esd.exercise3.server;

import kmitl.esd.exercise3.model.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Web service customer CRUD operations
 * produce endpoints as a part of the server API
 * Controller reacts to requests, controls the workflow, and give a response
 * RESTful webservice -> HTTP method calls.
 * Data formmat for dara objects is JSON
 *
 * Why the customer controller is called a controller
 * Answer 1.0 : Because Spring Framework named it like this
 * Answer 2.0 : WHy not the web service request handler, REST HTTP responder, or API enpoint implementer ......
 * Answer 3.0 : Web Service API Controller, because it handles (control) the requests of client and provides a response
 *             = it is in charge of the API= it controls the API
 */

//why is OOP a function/procedure is called a 'method"
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final AtomicLong counter = new AtomicLong();

    //ToDo test "database"
    private static List<CustomerDTO> customers=new ArrayList<>(); //ToDO map

    //ToDo replace with connection to database
    static {
        CustomerDTO customerTom = new CustomerDTO(1,"Tom",35);
        CustomerDTO customerSuzy = new CustomerDTO(2,"Suzy",53);
        customers.add(customerTom);
        customers.add(customerSuzy);

    }

    /**
     * REST web service endpoint Read = find customer
     * GET (HTTP) method for finding all customer
     * Example: http://localhost:8888/customer/findall
     * @return list of all customers
     */
    @GetMapping("/findall")
    public List<CustomerDTO> getAllCustomers() {
        return customers; //ToDo use customer service and database
    }

    /**
     * create a customer
     * @param customer as a request body
     * @return the saved version of the customer
     */
    // ToDo how we can consume JSON objects as a body
    @PostMapping
    public CustomerDTO createCustomer(CustomerDTO customer){
        customers.add(customer);
        return customer;
    }

    //put-> update replace patch -> update modify

    /**
     * update/ modify a customer
     * @param customer as a req. body
     * @return the updated customer object or null if update not successful
     */
    @PutMapping
    public CustomerDTO updateCustomer(CustomerDTO customer){
        CustomerDTO customerToBeUpdated = customers.stream().filter(c->c.getId().equals(customer.getId())).findFirst().get();
        if(customerToBeUpdated!=null){ //exists -> replace
            customers.remove(customerToBeUpdated);
        }else{
            return null; // no customer found for the update
        }
        customers.add(customer);
        return customer;
    }

    /**
     * Delete a customer
     * @param customerId id of the customer
     * @return true if deleted, false if not found
     */
    @DeleteMapping("/{id}")
    public Boolean deleteCustomer(@PathVariable("id") Long customerId){

        CustomerDTO customerToBeDeleted = customers.stream().filter(c->c.getId().equals(customerId)).findFirst().get();
        if(customerToBeDeleted!=null){
            customers.remove(customerToBeDeleted);
            return true;
        }else{
            return false;
        }
    }

}