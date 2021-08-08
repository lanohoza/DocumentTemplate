package com.pdf.example.pdfDocument.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String idOrder;
    private String orderNum;
private float orderTotal;
    private float priceTTC;
    private float priceTVA;
    private List<Product> products;
    private String dateOrder;
    private float remise;



//fonction calculer total pour un seul produit
    public  float getTotalProduct(Product product) {
        float total=0;
            total = product.getPriceUnit() * product.getQuantity();
            return total;
    }

    public float calculTotalHT(){
        orderTotal=0;
       for (Product p:products){
           orderTotal=orderTotal+getTotalProduct(p);
       }
       return orderTotal;
    }

//fonction calculer le prix tva
    public  float calculTVA() {
       float pourcentage= (float) 0.19;
       priceTVA=(orderTotal-remise)*pourcentage;
      return priceTVA;
    }

//fonction calcul prix ttc

    public float calculTTC(){

        priceTTC=((orderTotal-remise)+priceTVA);
        return priceTTC;
    }
}
