package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="mst_company")
public class Company {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private String companyName;

    @OneToMany(mappedBy = StringConstant.COMPANY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<CompanyUser> companyUsers = new ArrayList<>();
    @OneToMany(mappedBy = StringConstant.COMPANY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    public Company(){
    }

    public Company(String companyName) {
        this.companyName = companyName;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CompanyUser> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<CompanyUser> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) &&
                Objects.equals(companyName, company.companyName) &&
                Objects.equals(companyUsers, company.companyUsers) &&
                Objects.equals(events, company.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyUsers, events);
    }
}
