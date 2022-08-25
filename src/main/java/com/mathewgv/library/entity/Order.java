package com.mathewgv.library.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 6286423933793015070L;

    private Long id;
    private User client;
    private Book book;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate factDate;
    private LoanType type;
    private OrderStatus status;

    public Order(Long id,
                 User client,
                 Book book,
                 LocalDate issueDate,
                 LocalDate dueDate,
                 LocalDate factDate,
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getFactDate() {
        return factDate;
    }

    public void setFactDate(LocalDate factDate) {
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
