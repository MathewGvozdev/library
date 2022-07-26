package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

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
