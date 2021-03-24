package kmitl.esd.exercise3.customer_service.repo;

import kmitl.esd.exercise3.customer_service.model.Quotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface QuotationRepository extends CrudRepository<Quotation,Long> {
    /**
     * find quote by date/time
     *
     * @param creationDateTime of quote to find
     * @return quotes with creation date time after/equal the
     */

    @Query("select q from Quotation q where q.creationDateTime >= :creationDateTime")
    List<Quotation> findAllWithCreationDateTimeAfter(
            @Param("customerId") LocalDateTime creationDateTime);


    /**
     * use the namedQuery with the name "Quotation.findByCustomer"
     *
     */
    List<Quotation> findByCustomerId(@Param("customerId") Long customerId );
}
