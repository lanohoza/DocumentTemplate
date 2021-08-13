package com.pdf.example.pdfDocument.models;

import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String idOrder;
    private String orderNum;
private double orderTotal;
    private double priceTTC;
    private double priceTVA;
    private List<Product> products;
    private String dateOrder;
    private double remise;



//fonction calculer total pour un seul produit
    public  double getTotalProduct(Product product) {
        float total;
            total = product.getPriceUnit() * product.getQuantity();
            return total;
    }

    public double calculTotalHT(){
        orderTotal=0;
       for (Product p:products){
           orderTotal=orderTotal+getTotalProduct(p);
       }
       return orderTotal;
    }

//fonction calculer le prix tva
    public  double calculTVA() {
       double pourcentage=  0.19;
       priceTVA=(orderTotal-remise)*pourcentage;
      return priceTVA;
    }

//fonction calcul prix ttc

    public double calculTTC(){

        priceTTC= ((orderTotal-remise)+priceTVA);
        return priceTTC;
    }
}
