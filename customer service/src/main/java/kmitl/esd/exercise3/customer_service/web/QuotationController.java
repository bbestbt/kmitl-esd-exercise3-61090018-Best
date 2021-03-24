package kmitl.esd.exercise3.customer_service.web;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.model.Quotation;
import kmitl.esd.exercise3.customer_service.service.CustomerService;
import kmitl.esd.exercise3.customer_service.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * API for customers (RestController)
 */
@RestController
public class QuotationController {

    @Autowired
    QuotationService quotationService;// Business logic
    /**
     * creating a get mapping that retrieves all the customers detail from the database
     * @return list of customer object as JSON
     */
    @GetMapping("/quotation")
    private List<Quotation> getAllQuotation(){
        return quotationService.getAllQuotations();
    }

    //creating a get mapping that retrieves the detail of a specific quotation
    @GetMapping("/quotation/{id}")
    private Quotation getQuotation(@PathVariable("id") Long id){
        return quotationService.getQuotationById(id);
    }



    //creating a delete mapping that deletes a specific quotation
    @DeleteMapping("/quotation/{id}")
    private void deleteQuotation(@PathVariable("id") Long id){
        quotationService.delete(id);
    }

    /**
     * creating post mapping that post the quotation detail in the database
     * via the service (business logic) and the repository (persistence)
     * @param quotation JSON object
     * @return the id (unique identifier) for the quotation
     */

    @PostMapping("/quotation")
    private Long saveQuotation(@RequestBody Quotation quotation){
        quotationService.saveOrUpdate(quotation);
        return quotation.getId();

    }

}
