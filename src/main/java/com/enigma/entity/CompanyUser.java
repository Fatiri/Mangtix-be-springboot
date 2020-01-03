package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="company_user")
public class CompanyUser {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;

    @Transient
    private String userIdTransient;
    @Transient
    private String companyIdTransient;

    @ManyToOne
    @JoinColumn(name = StringConstant.COMPANY_ID)
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JoinColumn(name = StringConstant.USER_ID)
    private User user;

    public CompanyUser() {
    }

    public CompanyUser(String userIdTransient, String companyIdTransient) {
        this.userIdTransient = userIdTransient;
        this.companyIdTransient = companyIdTransient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIdTransient() {
        if (userIdTransient==null){
            return getUser().getId();
        }
        return userIdTransient;
    }

    public void setUserIdTransient(String userIdTransient) {
        this.userIdTransient = userIdTransient;
    }

    public String getCompanyIdTransient() {
        return companyIdTransient;
    }

    public void setCompanyIdTransient(String companyIdTransient) {
        this.companyIdTransient = companyIdTransient;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyUser that = (CompanyUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userIdTransient, that.userIdTransient) &&
                Objects.equals(companyIdTransient, that.companyIdTransient) &&
                Objects.equals(company, that.company) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdTransient, companyIdTransient, company, user);
    }
}
