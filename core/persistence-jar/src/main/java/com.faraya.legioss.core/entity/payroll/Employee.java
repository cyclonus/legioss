package com.faraya.legioss.core.entity.payroll;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Contact;
import com.faraya.legioss.core.entity.payroll.agreement.Agreements;
import com.faraya.legioss.core.entity.security.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 9/8/15.
 */

@Entity
@Table(name = "payroll_employee")
public class Employee extends AbstractEntity implements IIdentifiable <Long>{

    public static final String NO_ID = "0";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_sec_number", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String socialSecurityNumber = NO_ID;

    @Column(name = "id_number", nullable = true)
    private String idNumber = NO_ID;

    @Column(name = "hired_date", nullable = false)
    private Date hireDate = Calendar.getInstance().getTime();

    @Column(name = "cease_date", nullable = true)
    private Date ceaseDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Contact> contacts;

    @JoinColumn(name = "user_id")
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    @Column(name = "user_id", nullable = true, updatable = false, insertable = false)
    public Long userId;

    @JoinColumn(name = "agreement_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Agreements agreement;

    @Column(name = "agreement_id", nullable = false, updatable = false, insertable = false)
    private Long agreementId;

    public Employee() {
    }

    public Employee(User user) {
        this.user = user;
    }

    public Employee(User user, Date hireDate) {
        this.user = user;
        this.hireDate = hireDate;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getCeaseDate() {
        return ceaseDate;
    }

    public void setCeaseDate(Date ceaseDate) {
        this.ceaseDate = ceaseDate;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Agreements getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreements agreement) {
        agreement.setEmployee(this);
        this.agreement = agreement;
    }

    public Long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    @Override
    public boolean isTransient() {
        return (getId() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!getId().equals(employee.getId())) return false;
        if (!getIdNumber().equals(employee.getIdNumber())) return false;
        if (!getHireDate().equals(employee.getHireDate())) return false;
        return !(getCeaseDate() != null ? !getCeaseDate().equals(employee.getCeaseDate()) : employee.getCeaseDate() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getIdNumber().hashCode();
        result = 31 * result + getHireDate().hashCode();
        result = 31 * result + (getCeaseDate() != null ? getCeaseDate().hashCode() : 0);
        return result;
    }
}
