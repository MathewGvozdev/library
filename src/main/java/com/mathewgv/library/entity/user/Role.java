package com.mathewgv.library.entity.user;

import com.mathewgv.library.entity.Entity;

import java.util.Objects;

public class Role extends Entity {

    private Integer id;
    private String title;

    public Role(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Role(String title) {
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
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
               "id=" + id +
               ", title='" + title + '\'' +
               '}';
    }
}
