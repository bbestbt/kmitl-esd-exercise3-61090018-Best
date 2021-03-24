package kmitl.esd.exercise3.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO class for a customer (API gateway/ API client)
 * DTO is a PoJo - Plain old Java object = we do not use any libraries or frameworks
 */
//@NoArgsConstructor
    @Setter
    @Getter
public class CustomerDTO implements Serializable {
    private Long id; //unique id
    private String name; //name of the customer
    private Long age; //age in years
    private String remark; //free text for customer

    /**
     * constructor
     */
    public CustomerDTO(){
        //
    }

    /**
     * constuctor
     * @param id
     * @param name
     * @return void
     */
    public CustomerDTO(long id, String name){
        this.id=id;
        this.name=name;
    }
// if use literal (long)-> 1L 35L

    /**
     * constructor
     * @param id
     * @param name
     * @param age
     * return void
     */
    public CustomerDTO(long id, String name, long age){
        this(id,name);
        this.age=age;
    }

    /**
     * print details
     * @return string customer details
     */
    @Override
    public String toString(){
        return Long.toString(id)+" , "+name+" , "+age;
    }

    /**
     * get id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     * @param id
     * @return void
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * get remark
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * set remark
     * @param remark
     * @return void
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
