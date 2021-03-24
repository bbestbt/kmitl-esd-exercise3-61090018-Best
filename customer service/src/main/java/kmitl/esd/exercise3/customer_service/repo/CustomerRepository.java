package kmitl.esd.exercise3.customer_service.repo;

import kmitl.esd.exercise3.customer_service.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * persistence of customers
 */
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    /**
     * find customers by name and ordered by name
     *??? exact match or like name???
     *
     * @param name of constructor to find
     * @return customers with the name or empty list
     */
    List<Customer> findByNameOrderByName(String name);
//
//    List<Customer> findByNameOrderByAge(String name);
//
//    List<Customer> findByNameAndAgeOrderById(String name,Integer age);
}
