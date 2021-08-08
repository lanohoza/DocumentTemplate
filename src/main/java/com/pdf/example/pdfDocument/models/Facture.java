package com.pdf.example.pdfDocument.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {

    private String idFacture;
    private String numFacture;
    private String date;
    private String date_echance;


}
