package hva.ewa.model.embeddable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hva.ewa.model.Customer;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class EquipmentId implements Serializable {

    @Column(name = "type")
    private String type;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private Customer customer;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
