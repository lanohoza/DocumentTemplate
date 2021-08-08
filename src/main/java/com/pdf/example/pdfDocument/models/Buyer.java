package com.pdf.example.pdfDocument.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {
    private String idBuyer;
    private String firstname;
    private String lastname;
    private String birthday;
    private String phone;
    private String address;
    private String NIF; //Numero matricul fiscal
    private String NRC;
    private String ART;

}
