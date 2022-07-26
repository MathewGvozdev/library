package com.mathewgv.library.entity.book;

import com.mathewgv.library.entity.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookMeta extends Entity {

    private Integer id;
    private String title;
    private String series;
    private List<Author> authors;
    private List<Genre> genres;

    public BookMeta(Integer id,
                    String title,
                    String series,
                    List<Author> authors,
                    List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.series = series;
        this.authors = authors;
        this.genres = genres;
    }
    public BookMeta(Integer id,
                    String title,
                    List<Author> authors,
                    List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public BookMeta(String title, String series, List<Author> authors, List<Genre> genres) {
        this.title = title;
        this.series = series;
        this.authors = authors;
        this.genres = genres;
    }

    public BookMeta(String title, List<Author> authors, List<Genre> genres) {
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookMeta bookMeta = (BookMeta) o;
        return id.equals(bookMeta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookMeta{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", series='" + series + '\'' +
               ", authors=" + authors +
               ", genres=" + genres +
               '}';
    }
}
