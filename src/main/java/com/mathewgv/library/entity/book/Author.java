package com.mathewgv.library.entity.book;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {

    @Serial
    private static final long serialVersionUID = 1741986172323358987L;
    private Integer id;
    private String firstName;
    private String surname;

    public Author(Integer id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Author(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", surname='" + surname + '\'' +
               '}';
    }
}
