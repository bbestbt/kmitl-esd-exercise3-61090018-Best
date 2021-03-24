package kmitl.esd.exercise3.customer_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table//class name is table
@NamedQuery(name="Quotation.findAll",query = "SELECT q FROM Quotation q ORDER BY q.customer.id,q.id")
@NamedQuery(name="Quotation.findByCustomerId",query ="SELECT q FROM Quotation q WHERE q.customer.id =: customerId ORDER by q.id" )
@NoArgsConstructor //create constructor with no arg
@Getter
@Setter // getter and setter for all properties
public class Quotation {

    @Id //primary key in DB
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id ;// id is primary key

    @Column @NonNull @Size(min=3,max=240) //contraints : name is min. 3 char and max.
    private String reference; //quote name

    @ManyToOne @NotNull // a quote has ONE customer, a customer has MANY quotes
    private Customer customer; //customer who receives the quote

    @NonNull @Min(0)
    private Float price;

    @Column @Past
    private LocalDateTime creationDateTime;

    @Column @Future //constraint for temporal field: valid TS must be in the future
    private LocalDateTime validUntilDateTime;

    @Column
    private LocalDateTime saveDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quotation(Long id, String reference, Float price, Customer customer){
        this.id=id;
        this.reference=reference;
        this.price=price;
        this.customer=customer;
    }

    @PreUpdate @PrePersist
    public void setSaveTimeStamp(){
        this.saveDateTime= LocalDateTime.now();
    }
}
