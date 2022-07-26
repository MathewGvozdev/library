package com.mathewgv.library.service.dto;

import com.mathewgv.library.entity.order.LoanType;
import com.mathewgv.library.entity.order.OrderStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OrderDto {

    Long id;
    Integer clientId;
    String client;
    Integer bookId;
    String bookTitle;
    String issueDate;
    String dueDate;
    String factDate;
    String loanType;
    String status;
}
