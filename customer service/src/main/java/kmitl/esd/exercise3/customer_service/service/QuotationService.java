package kmitl.esd.exercise3.customer_service.service;

import kmitl.esd.exercise3.customer_service.model.Customer;
import kmitl.esd.exercise3.customer_service.model.Quotation;
import kmitl.esd.exercise3.customer_service.repo.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * quote service = business logic for offering
 */
@Service
public class QuotationService {

    Logger logger = Logger.getLogger(QuotationService.class.toString());
    @Autowired
    QuotationRepository quotationRepository; //persistence for quote

//    @Autowired
//    CustomerService customerService; //persistence for quote object

    /**
     * get all quotation
     * @return list of all quotation
     */
    public List<Quotation> getAllQuotations() {
        List<Quotation> quotations=new ArrayList<>();
        quotationRepository.findAll().forEach(quote->quotations.add(quote));
        return quotations;

    }
//
//    /**
//     * get all quotation with a specific name
//     *
//     * @param creationDateTime of the quotation
//     * @return quotations with the name
//     */
//    public List<Quotation> getQuotation(LocalDateTime creationDateTime){
//        List<Quotation> quotations=new ArrayList<>();
//        quotationRepository.findAllWithCreationDateTimeAfter(creationDateTime).forEach(quotation -> quotations.add(quotation));
//        return  quotations;
//    }

//    public List<Quotation> getQuotationByCustomerId(Long customerId){
//        return quotationRepository.findByCustomerId(customerId);
//    }

    //getting a specific record
    public Quotation getQuotationById(Long id) {
        return quotationRepository.findById(id).get();
    }

    /**
     * save or update(upsert) for quotation object
     *
     * @param quotation to be updated or inserted
     */
    public void saveOrUpdate(Quotation quotation) {
//        if (quotation.getCustomer() != null) {
//            if (quotation.getCustomer().getId == null) { //customer does not exist in
//            } else {//customer exist in DB
//                Customer customer = customerService.getCustomerById(quotation.getCustomer);
//                logger.info("customer" + customer.toString());
//            }
//        }
        quotationRepository.save(quotation);
    }

    public void delete(Long id){
        quotationRepository.deleteById(id);
    }

}
