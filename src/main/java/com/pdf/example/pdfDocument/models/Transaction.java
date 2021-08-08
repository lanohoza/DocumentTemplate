package com.pdf.example.pdfDocument.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int idTransaction;
    private String TransactionNum;
    private String TransactionDate;
    private String paymentMethod;


}
