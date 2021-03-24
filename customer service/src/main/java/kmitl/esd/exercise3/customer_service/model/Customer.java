package kmitl.esd.exercise3.customer_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * customer entity for persistence
 * javax persistence -> JPA -> Impl.. : Hibernate -> Spring JPA includes Hibernate
 */
@Entity
@Table//class name is table
//@Table(name="customerTable")  //table name in the DB is
@NoArgsConstructor //lombok create constructor with no arg
@Getter @Setter // lombok: getter and setter for all properties
public class Customer implements ICustomer{

    @Id //primary key in DB
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id ;// id is primary key

    @Column @NonNull @Size(min=3,max=240) //contraints : name is min. 3 char and max.
    private String name; //customer name

    @Transient //value will not be saved in the DB
    private String tempRemark;

    @Column @Min(18) @Max(120) //age of a person is between 18 and 120
    private Integer age; //age in years

    @Column @Past
    private LocalDate birthday;

    @Column @Pattern(regexp = "\\(?\\d{8,3}\\)?\\d{3}-\\d{4}") //test with https://regexr.com/
    private String phoneNumber; //customer's main email address

    @Column @Pattern(regexp = ".*@.*\\..*")
    private String email;

    @Column @Past //creation of a customer  must be in the past
    private LocalDateTime creationDateTime;

//    @OneToMany
//    protected List<Quotation> quotationList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Constructor with main customer data
     * @param id customer id
     * @param name name of the customer
     * @param age age in years
     */

    public Customer(Long id,String name,Integer age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    /**
     * constructor with customer data:name and age
     * @param name name of the customer
     * @param age age in years
     */
    public Customer(String name,int age){
        this.name=name;
        this.age=age;
    }
}
