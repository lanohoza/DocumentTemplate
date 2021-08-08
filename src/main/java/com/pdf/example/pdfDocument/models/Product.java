package com.pdf.example.pdfDocument.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String image;
    private String category;
    private float priceUnit;
    private String unityProduct;
    private int quantity;
    private String REF;


}
