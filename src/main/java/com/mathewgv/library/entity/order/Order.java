package com.mathewgv.library.entity.order;

import com.mathewgv.library.entity.book.Book;
import com.mathewgv.library.entity.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private Long id;
    private User client;
    private Book book;
    private Date issueDate;
    private Date dueDate;
    private Date factDate;
    private LoanType type;
    private OrderStatus status;

    public Order(Long id,
                 User client,
                 Book book,
                 Date issueDate,
                 Date dueDate,
                 Date factDate,
                 LoanType type,
                 OrderStatus status) {
        this.id = id;
        this.client = client;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.factDate = factDate;
        this.type = type;
        this.status = status;
    }

    public Order(Long id,
                 User client,
                 Book book,
                 Date issueDate,
                 Date dueDate,
                 LoanType type,
                 OrderStatus status) {
        this.id = id;
        this.client = client;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.type = type;
        this.status = status;
    }

    public Order(User client,
                 Book book,
                 Date issueDate,
                 Date dueDate,
                 LoanType type,
                 OrderStatus status) {
        this.client = client;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.type = type;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User user) {
        this.client = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getFactDate() {
        return factDate;
    }

    public void setFactDate(Date factDate) {
        this.factDate = factDate;
    }

    public LoanType getType() {
        return type;
    }

    public void setType(LoanType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", user=" + client +
               ", book=" + book +
               ", issueDate=" + issueDate +
               ", dueDate=" + dueDate +
               ", factDate=" + factDate +
               ", type=" + type +
               ", status=" + status +
               '}';
    }
}
