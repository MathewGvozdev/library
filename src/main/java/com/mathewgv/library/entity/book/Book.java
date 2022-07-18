package com.mathewgv.library.entity.book;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

    private Integer id;
    private Publisher publisher;
    private BookMeta bookMeta;
    private Integer pages;
    private Integer publicationYear;

    public Book(Integer id,
                Publisher publisher,
                BookMeta bookMeta,
                Integer pages,
                Integer publicationYear) {
        this.id = id;
        this.publisher = publisher;
        this.bookMeta = bookMeta;
        this.pages = pages;
        this.publicationYear = publicationYear;
    }

    public Book(Publisher publisher,
                BookMeta bookMeta,
                Integer pages,
                Integer publicationYear) {
        this.publisher = publisher;
        this.bookMeta = bookMeta;
        this.pages = pages;
        this.publicationYear = publicationYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BookMeta getBookMeta() {
        return bookMeta;
    }

    public void setBookMeta(BookMeta bookMeta) {
        this.bookMeta = bookMeta;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", publisher=" + publisher +
               ", bookMeta=" + bookMeta +
               ", pages=" + pages +
               ", publicationYear=" + publicationYear +
               '}';
    }
}
