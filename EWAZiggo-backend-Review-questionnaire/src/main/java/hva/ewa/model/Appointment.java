package hva.ewa.model;

import hva.ewa.model.embeddable.AppointmentId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Appointment implements Serializable {

    @EmbeddedId
    private AppointmentId id;

    @Basic
    @Column(name = "status", nullable = false)
    private String status;

    public AppointmentId getId() {
        return id;
    }

    public void setId(AppointmentId id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
