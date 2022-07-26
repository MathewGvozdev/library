package com.mathewgv.library.entity.book;

import com.mathewgv.library.entity.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Genre extends Entity {

    private Integer id;
    private String title;

    public Genre(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Genre(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id.equals(genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Genre{" +
               "id=" + id +
               ", name='" + title + '\'' +
               '}';
    }
}
