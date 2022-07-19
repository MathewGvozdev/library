package com.mathewgv.library.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderCreationDto {

    Long id;
    Integer userId;
    Integer bookId;
    String issueDate;
    String dueDate;
    String factDate;
    String loanType;
    String status;
}
