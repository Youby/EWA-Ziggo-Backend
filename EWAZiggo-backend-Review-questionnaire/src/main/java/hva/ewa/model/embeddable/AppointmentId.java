package hva.ewa.model.embeddable;

import hva.ewa.model.Customer;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class AppointmentId implements Serializable {

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private Customer customer;


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
