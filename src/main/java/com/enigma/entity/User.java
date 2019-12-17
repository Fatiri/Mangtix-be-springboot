package com.enigma.entity;

import com.enigma.constanta.StringConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mst_user")
public class User {
    @Id
    @GeneratedValue(generator = StringConstant.SYSTEM_UUID2)
    @GenericGenerator(name = StringConstant.SYSTEM_UUID2, strategy = StringConstant.UUID2)
    private String id;
    private String userName;
    private String password;
    private String fullName;
    private String bornPlace;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public User() {
    }

    public User(String userName, String password, String fullName, String bornPlace, Date birthDate, Date createAt){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.bornPlace = bornPlace;
        this.birthDate = birthDate;
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(bornPlace, user.bornPlace) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(createAt, user.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, fullName, bornPlace, birthDate, createAt);
    }
}


