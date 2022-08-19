package com.mathewgv.library.entity.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2397967700130622206L;

    private Integer id;
    private User user;
    private String firstName;
    private String surname;
    private String telephone;
    private String passportNumber;

    public UserInfo(Integer id,
                    User user,
                    String firstName,
                    String surname,
                    String telephone,
                    String passportNumber) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.surname = surname;
        this.telephone = telephone;
        this.passportNumber = passportNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id.equals(userInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
               "id=" + id +
               ", user=" + user +
               ", firstName='" + firstName + '\'' +
               ", surname='" + surname + '\'' +
               ", telephone='" + telephone + '\'' +
               ", passportNumber='" + passportNumber + '\'' +
               '}';
    }
}
