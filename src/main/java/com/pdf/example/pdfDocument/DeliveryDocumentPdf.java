package com.pdf.example.pdfDocument;

import com.github.royken.converter.FrenchNumberToWords;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.example.pdfDocument.models.*;

import java.io.File;
import java.io.FileOutputStream;


public class DeliveryDocumentPdf {



    public boolean Deliverypdf(Order order, Buyer buyer,Enterprise seller, Transaction transaction,Delivery delivery,String path){


        try {
            //Create new document
            Document document =new Document();
            FileOutputStream fos=new FileOutputStream(new File(path+"Bon_Livraison"+delivery.getIdDelivery()+".pdf"));
            PdfWriter writer=PdfWriter.getInstance(document,fos);
            document.open();
            document.addTitle("Delivery");
            //les font utiliser
            Font cellFont=FontFactory.getFont(FontFactory.TIMES_ROMAN,15,1,BaseColor.BLACK);
            Font factureFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,15,1);
            Font DoitFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,13,1);
            Font underline= FontFactory.getFont(FontFactory.TIMES_ROMAN,15,4);
            Font infoFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,12);
            Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10F, BaseColor.WHITE);
//Bar orange
            PdfPTable t=new PdfPTable(1);
            t.setWidthPercentage(100);
            PdfPCell c=new PdfPCell();
            c.setBackgroundColor(new BaseColor(255,87,34));
            c.setFixedHeight(10);
            c.setBorder(0);
            t.addCell(c);
            document.add(t);
//Bar orange
//tab contient les infos de vendeur
            PdfPTable tab=new PdfPTable(2);
            tab.setWidthPercentage(100);
            tab.setWidths(new int[]{20,70});
            PdfPCell cell1=new PdfPCell();
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setHorizontalAlignment(0);
           // cell1.setFixedHeight(80);
            cell1.setBackgroundColor(new BaseColor(243, 243, 243));
//logo
            Image logo;
            String pathimg="logo.png";
            String s=seller.getLogo();
            if(s==null) {
                logo = Image.getInstance(pathimg, true);
            }
            else {
                logo = Image.getInstance(seller.getLogo(), true);
            }
            logo.setWidthPercentage(40);
            cell1.setHorizontalAlignment(1);
            cell1.setImage(logo);
            tab.addCell(cell1);

     //Info vendeur
            cell1.setHorizontalAlignment(0);
            cell1.setPhrase(new Phrase("\nNom de société: "+seller.getCompanyName()+"\n\nAdresse et contact: "+seller.getAddress()+" /"+seller.getPhone()+"\n\nLes détails fiscaux et bancaires: les suivants: \n",cellFont));
            tab.addCell(cell1);
            tab.addCell(cell1);
            document.add(tab);
            PdfPTable tab3=new PdfPTable(1);
            tab3.setWidthPercentage(100);
            PdfPCell cell3=new PdfPCell();
            cell3.setBorder(PdfPCell.NO_BORDER);
            cell3.setBackgroundColor(new BaseColor(243, 243, 243));
            cell3.setPhrase(new Phrase("NRC: "+seller.getNRC() +" - NIF: "+seller.getNRC()+" - NIS: "+seller.getNIS()+" - ART: "+seller.getART()+" - Numero de compte bancaire: "+seller.getAccountNumber(),infoFont));
            cell3.setPaddingBottom(20);
            tab3.addCell(cell3);
            document.add(tab3);

//la fin de l'entete infos vendeur

//Les information d'achetur
            PdfPTable tabBayer=new PdfPTable(2);
            tabBayer.setWidthPercentage(100);
            PdfPCell cellBayer=new PdfPCell();
            cellBayer.setBorder(PdfPCell.NO_BORDER);
            tabBayer.setWidths(new int[]{60,50});

            cellBayer.setBackgroundColor(BaseColor.WHITE);
            cellBayer.setPhrase(new Phrase("BON DE LIVRAISON N° BL: "+delivery.getNumDelivery(),factureFont));
            tabBayer.addCell(cellBayer);
            PdfPTable td=new PdfPTable(2);
            td.setWidthPercentage(100);
            td.setWidths(new int[]{20,80});
            PdfPCell cel=new PdfPCell();
            cel.setBorder(PdfPCell.NO_BORDER);
            cel.setPhrase(new Phrase("Doit:",DoitFont));
            td.addCell(cel);
            cel.setPhrase(new Phrase("Code client:"+buyer.getIdBuyer(),DoitFont));
            cel.setHorizontalAlignment(2);
            td.addCell(cel);
            cellBayer.addElement(td);
            tabBayer.addCell(cellBayer);
            cellBayer.setBackgroundColor(new BaseColor(243, 243, 243));
            cellBayer.setPhrase(new Phrase("\nDate: "+delivery.getDateDelivery()+"\n\nN° de commande: "+order.getOrderNum()+"\n\nMode de paiement: "+transaction.getPaymentMethod(),infoFont));
            tabBayer.addCell(cellBayer);
            cellBayer.setPhrase(new Phrase("Nom de client: "+buyer.getFirstname()+" "+buyer.getLastname()+"\n\nAdresse et contact: "+buyer.getAddress()+"/ "+buyer.getPhone(),infoFont));
            tabBayer.addCell(cellBayer);

            document.add(tabBayer);
            document.add(new Paragraph("\n"));
//table contient la liste des produits
            PdfPTable table=new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1,2,4,1,2,3,3});

    PdfPCell headerCell = new PdfPCell();
    headerCell.setBorderColor(new BaseColor(255,87,34));
    headerCell.setBackgroundColor(new BaseColor(255,87,34));
    headerCell.setBorderWidth(1);
    headerCell.setFixedHeight(17);
    headerCell.setHorizontalAlignment(1);

    headerCell.setPhrase(new Phrase("N", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("REF", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("DESCRIPTION", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("UNITE", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("QUANTITE", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("PRIX UNITAIRE HT", headerFont));
    table.addCell(headerCell);

    headerCell.setPhrase(new Phrase("TOTAL", headerFont));
    table.addCell(headerCell);

    PdfPCell dataCell = new PdfPCell();
    dataCell.setBorderColor(new BaseColor(68, 58, 58));
    dataCell.setPaddingBottom(5);
    dataCell.setHorizontalAlignment(0);
    dataCell.setBorderWidthTop(0);
    dataCell.setBorderColorTop(BaseColor.WHITE);
    Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);

    for (Product product : order.getProducts()) {
        dataCell.setHorizontalAlignment(2);
        dataCell.setPhrase(new Phrase(String.valueOf(product.getIdProduct()), dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(String.valueOf(product.getREF()), dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(product.getDescriptionProduct(), dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(product.getUnityProduct(), dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(String.valueOf(product.getQuantity()), dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(String.valueOf(product.getPriceUnit())+" DA", dataFont));
        table.addCell(dataCell);


        dataCell.setPhrase(new Phrase(String.valueOf(order.getTotalProduct(product))+" DA", dataFont));
        table.addCell(dataCell);
}

    PdfPCell del=new PdfPCell();
    del.setBorderWidth(0);
    del.setPhrase(new Phrase(""));
    table.addCell(del);
    del.setPhrase(new Phrase(""));
            table.addCell(del);
    del.setPhrase(new Phrase(""));
            table.addCell(del);
    del.setPhrase(new Phrase(""));
            table.addCell(del);
    del.setPhrase(new Phrase(""));
            table.addCell(del);
//ajouter Le total HR au cellule de table
             del.setPhrase(new Phrase("TOTAL HR",dataFont));
            table.addCell(del);
            del.setHorizontalAlignment(2);
            del.setPhrase(new Phrase(String.valueOf(order.getOrderTotal())+" DA",dataFont));
            table.addCell(del);

            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setHorizontalAlignment(0);
//ajouter Le total TVA au cellule de table
            del.setPhrase(new Phrase("TVA",dataFont));
            table.addCell(del);
            del.setHorizontalAlignment(2);
            del.setPhrase(new Phrase(String.valueOf(order.getPriceTVA())+" DA",dataFont));
            table.addCell(del);

            del.setHorizontalAlignment(0);
            del.setPhrase(new Phrase("",cellFont));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setHorizontalAlignment(0);
//ajouter remise au cellule de table
            del.setPhrase(new Phrase("Remise",dataFont));
            table.addCell(del);
            del.setHorizontalAlignment(2);
            del.setPhrase(new Phrase(order.getRemise()+" DA",dataFont));
            table.addCell(del);

            del.setHorizontalAlignment(0);
            del.setPhrase(new Phrase("",cellFont));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setPhrase(new Phrase(""));
            table.addCell(del);
            del.setHorizontalAlignment(0);
//ajouter le otal TTc au cellule de table
            del.setPhrase(new Phrase("TOTAL TTC",dataFont));
            table.addCell(del);
            del.setHorizontalAlignment(2);
            del.setBackgroundColor(new BaseColor(255,87,34));
            del.setPhrase(new Phrase((order.getPriceTTC())+" DA",dataFont));
            table.addCell(del);

            document.add(table);


Chunk Signefournisseur=new Chunk("\nVisa du fournisseur",underline);

document.add(Signefournisseur);
            document.add(new Paragraph("\n\n\n\n"));
// Information additionnelles
            PdfPTable tab2=new PdfPTable(2);
            tab2.setWidthPercentage(100);
            tab2.setWidths(new int[]{50,50});
            tab2.setTotalWidth(523);
            PdfPCell cell2=new PdfPCell();
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setHorizontalAlignment(0);

            cell2.setBackgroundColor(new BaseColor(255,87,34));

            cell2.setPhrase(new Phrase("Société à la capital de: "+seller.getSociale_capitale()+"\n\nAdresse GPS: "+seller.getGPS(),headerFont));
            tab2.addCell(cell2);

            cell2.setPhrase(new Phrase("Email: "+seller.getEmail()+"\n\nSite web: "+seller.getWebSite(),headerFont));
            tab2.addCell(cell2);
            FooterTable even=new FooterTable(tab2);
            writer.setPageEvent(even);
//fermer le document
            document.close();


            return true; }
        catch (Exception e){
            //s'il ya un exception de path
            System.out.println(e.toString());
                return false;
        }

    }
}
