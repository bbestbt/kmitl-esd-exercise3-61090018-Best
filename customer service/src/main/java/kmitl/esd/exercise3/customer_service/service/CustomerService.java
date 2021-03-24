package kmitl.esd.exercise3.customer_service.service;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.repo.CustomerRepository;
import kmitl.esd.exercise3.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * customer service = business logic for customer master data
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository; //persistence for customer, object injection

    /**
     * get all customer
     * @return list of all customer
     */
    public List<Customer> getAllCustomer(){
//        LocalDate now=LocalDate.now();
        List<Customer> customers=new ArrayList<>();
        customerRepository.findAll() // ToDo redundant with get..
                .forEach(customer -> {
//                    customer.setAge(...); // F fail
                    updateAge(customer);
                        customers.add(customer)});
        return customers;
    }

    /**
     * get all customer with specific name
     * @param name of the customer
     * @return customer with the name
     */
    public List<Customer> getCustomer(String name){
        List<Customer> customers= new ArrayList<Customer>();
        customerRepository.findByNameOrderByName(name)
                .forEach(customer->{ //DRY - Dont repeat yourself
                    updateAge(customer);
                        customers.add(customer);
                });
        return customers;
    }

    //getting a specific record
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).get();
    }

    /**
     * save or update (upsert) for customer object
     * @param customer to be updated or inserted
     */
    public void saveOrUpdate(Customer customer){
        customerRepository.save(customer);
    }

    //deleting a specific record
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

    /**
     * calculate the age based on now and the birthday
     *
     * @param customer must have a birthday
     * @exception NullPointerException when no birthday exist
     * ToDo should we deal with missing birthday? yes, if the birthday is
     * ToDo shouldn't be the age calculation in the Customer class?
     */
    public void updateAge(Customer customer){
        LocalDate now=LocalDate.now();
        customer.setAge(customer.getBirthday().until(now).getYears());
    }


//    public static CustomerService INSTANCE = new CustomerService(); //singleton
//    private List<CustomerDTO> customerDTO = new ArrayList<>();// customer list
//
//    /**
//     * private constructor
//     */
//    private CustomerService(){
//
//    }
//
//    /**
//     * function to get instance CustomerService
//     * @return INSTANCE singleton instance CustomerService
//     */
//    public static CustomerService getInstance(){
//        if (INSTANCE != null) {
//            return INSTANCE;
//        }
//
//        INSTANCE = new CustomerService();
//
//        return INSTANCE;
//
//    }
//
//    /**
//     * get all customer
//     * @return list of all customers
//     */
//    public List<CustomerDTO> getCustomer(){
//        return customerDTO;
//    }
//
//    /**
//     * create customer
//     * @param customer as a request body
//     * @return the saved version of the customer
//     */
//    public CustomerDTO createCustomer(CustomerDTO customer){
//        customerDTO.add(customer);
//        return customer;
//    }
//
//    /**
//     * Delete a customer
//     * @param customerId id of the customer
//     * @return true if deleted, false if not found
//     */
//    public Boolean deleteCustomer(Long customerId){
//        CustomerDTO customerToBeDeleted = customerDTO.stream().filter(c->c.getId().equals(customerId)).findFirst().get();
//        if(customerToBeDeleted!=null){
//            customerDTO.remove(customerToBeDeleted);
//            return true;
//        }else{
//            return false;
//        }
//
//    }
//
//    /**
//     * update/ modify a customer
//     * @param customer as a request body
//     * @return the updated customer object or null if update not successful
//     */
//    public CustomerDTO updateCustomer(CustomerDTO customer){
//        CustomerDTO customerToBeUpdated = customerDTO.stream().filter(c->c.getId().equals(customer.getId())).findFirst().get();
//        if(customerToBeUpdated!=null){ //exists -> replace
//            customerDTO.remove(customerToBeUpdated);
//        }else{
//            return null; // no customer found for the update
//        }
//        customerDTO.add(customer);
//        return customer;
//    }
//

}
