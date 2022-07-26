package com.mathewgv.library.entity.book;

import com.mathewgv.library.entity.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Publisher extends Entity {

    private Integer id;
    private String title;
    private String city;

    public Publisher(Integer id, String title, String city) {
        this.id = id;
        this.title = title;
        this.city = city;
    }

    public Publisher(String title, String city) {
        this.title = title;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publisher{" +
               "id=" + id +
               ", name='" + title + '\'' +
               ", city='" + city + '\'' +
               '}';
    }
}
