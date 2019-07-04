package hva.ewa.model;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Customer extends User implements Serializable {

    @Basic

    @Column(name = " customerCode", nullable = true)

    private String customerCode;

    @Basic
    @Column(name = "address", nullable = true)
    private String address;
    @Basic
    @Column(name = "birthdate", nullable = true)
    private Date birthdate;

    @Basic
    @Column(name = "phone", nullable = true)
    private String phone;

    @Basic
    @Column(name = "mobilePhone")
    private String mobilePhone;

   @OneToMany(fetch = FetchType.EAGER)
    private Collection<Questionnaire> issues;


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void addIssues(Questionnaire questionnaire){
        issues.add(questionnaire);
    }
    public Collection<Questionnaire> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Questionnaire> issues) {
        this.issues = issues;
    }


    @Override
    public String toString() {
        return super.toString() +  "Customer{" +
                "customerCode='" + customerCode + '\'' +
                ", address='" + address + '\'' +
                ", birthdate=" + birthdate +
                ", phone='" + phone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
