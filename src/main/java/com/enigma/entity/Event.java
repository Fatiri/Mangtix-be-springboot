package com.enigma.entity;

import com.enigma.constanta.EventConstanta;
import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = EventConstanta.EVENT_TABLE)
public class Event {

    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private String eventName;
    private String descriptionEvent;
    private Boolean publishStatus;
    @ManyToOne
    @JoinColumn(name = EventConstanta.COMPANY_ID_RELATION)
    private Company company;

    @Transient
    private String companyIdTransient;



    @OneToMany(mappedBy = EventConstanta.EVENT_MAPPED_BY, cascade = CascadeType.ALL)
    private List<EventDetail> eventDetailList;
    public Event() {
    }

    public Event(String eventName, String descriptionEvent, Boolean publishStatus) {
        this.eventName = eventName;
        this.descriptionEvent = descriptionEvent;
        this.publishStatus = publishStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public Boolean getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Boolean publishStatus) {
        this.publishStatus = publishStatus;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }


    public List<EventDetail> getEventDetailList() {
        return eventDetailList;
    }

    public void setEventDetailList(List<EventDetail> eventDetailList) {
        this.eventDetailList = eventDetailList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyIdTransient() {
        return companyIdTransient;
    }

    public void setCompanyIdTransient(String companyIdTransient) {
        this.companyIdTransient = companyIdTransient;
    }
}
