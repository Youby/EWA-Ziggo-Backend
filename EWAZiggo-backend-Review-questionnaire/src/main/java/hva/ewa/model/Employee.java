package hva.ewa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Employee extends User implements Serializable {

    @Basic
    @Column(name = "department")
    private String department;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
