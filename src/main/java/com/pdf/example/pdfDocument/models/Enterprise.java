package com.pdf.example.pdfDocument.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {
    private String idCompany;
    private String companyName;
    private String webSite;
    private String email;
    private String logo;
    private String phone;
    private String address;
    private String NIF; //Numero matricul fiscal
    private String NRC; //Num register comercial
    private String NIS;//numero d'dentification statique
    private String ART;//Article d'imposition
    private String accountNumber;
    private String sociale_capitale;
    private String GPS;




}
