package hva.ewa.model;


import hva.ewa.model.embeddable.EquipmentId;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Equipment implements Serializable {

    @EmbeddedId
    private EquipmentId id;

    @Basic
    @Column(name = "model", nullable = false)
    private String model;

    public EquipmentId getId() {
        return id;
    }

    public void setId(EquipmentId id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
