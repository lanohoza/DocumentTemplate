package com.pdf.example.pdfDocument;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.pdf.example.pdfDocument.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class factDocumentPdf {



    public boolean Orderpdf(Order order, Buyer buyer, Enterprise seller, String path, Transaction transaction) throws FileNotFoundException, DocumentException {

        Font DoitFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,10,1);
        Font infoFont= FontFactory.getFont(FontFactory.TIMES_ROMAN,8.5F);
        Font underline= FontFactory.getFont(FontFactory.TIMES_ROMAN,15,4);
        try {
            //Create new document
            Document document =new Document();
            FileOutputStream fos=new FileOutputStream(new File(path+"Bon_Commande"+order.getIdOrder()+".pdf"));
            PdfWriter.getInstance(document,fos);
            document.open();
            document.addTitle("Bon commande");

            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN,36, new BaseColor(5, 179, 194));
            Paragraph p=new Paragraph("Bon de commande N° "+ order.getOrderNum(),font);
            p.setAlignment(Element.ALIGN_LEFT);
            document.add(p);
            document.add(new Paragraph("\n"));

            LineSeparator ls = new LineSeparator();
            ls.setLineWidth(3);
            ls.setLineColor(new BaseColor(5, 179, 194));
            document.add(ls);
            document.add(new Paragraph("\n"));
//tab1
            PdfPTable tabBayer=new PdfPTable(2);
            tabBayer.setWidthPercentage(100);
            PdfPCell cellBayer=new PdfPCell();
            cellBayer.setBorder(PdfPCell.NO_BORDER);
            tabBayer.setWidths(new int[]{50,50});


            cellBayer.setPhrase(new Phrase(""));
            tabBayer.addCell(cellBayer);

            cellBayer.setPhrase(new Phrase("Doit:                      code client: "+buyer.getIdBuyer(),DoitFont));
            tabBayer.addCell(cellBayer);

            cellBayer.setBackgroundColor(new BaseColor(243, 243, 243));
            cellBayer.setPhrase(new Phrase("Date: "+order.getDateOrder()+"\n\nMode de paiement: "+transaction.getPaymentMethod(),infoFont));
            tabBayer.addCell(cellBayer);
            cellBayer.setPhrase(new Phrase("Nom de client: "+buyer.getFirstname()+" "+buyer.getLastname()+"\n\nAdresse et contact: "+buyer.getAddress()+" "+buyer.getPhone(),infoFont));
            tabBayer.addCell(cellBayer);

            document.add(tabBayer);
            document.add(new Paragraph("\n"));

//tab2
            PdfPTable table=new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1,2,4,1,2,3,3});

    PdfPCell headerCell = new PdfPCell();
    headerCell.setBorderColor(new BaseColor(0, 176, 240));
    headerCell.setBackgroundColor(new BaseColor(0, 176, 240));
    headerCell.setBorderWidth(1);
    headerCell.setFixedHeight(17);
    headerCell.setHorizontalAlignment(1);
    Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10F, BaseColor.WHITE);
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
    dataCell.setHorizontalAlignment(0);
    dataCell.setBorderWidthTop(0);
    dataCell.setBorderColorTop(BaseColor.WHITE);
    Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 7.5F, BaseColor.BLACK);

    for (Product product : order.getProducts()) {
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

        dataCell.setHorizontalAlignment(2);
        dataCell.setPhrase(new Phrase(String.valueOf(product.getPriceUnit())+" DA", dataFont));
        table.addCell(dataCell);

        dataCell.setHorizontalAlignment(2);
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
            del.setPhrase(new Phrase("TVA",dataFont));
            table.addCell(del);
            del.setHorizontalAlignment(2);
            del.setPhrase(new Phrase(String.valueOf(order.getPriceTVA())+" DA",dataFont));
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
            del.setPhrase(new Phrase("TOTAL TTC",dataFont));
            table.addCell(del);

            del.setHorizontalAlignment(2);
            del.setBackgroundColor(new BaseColor(0, 176, 240));
            del.setPhrase(new Phrase(String.valueOf(order.getPriceTTC())+" DA",dataFont));
            table.addCell(del);

            document.add(table);

            Chunk Signefournisseur=new Chunk("\nVisa du fournisseur",underline);

            document.add(Signefournisseur);
            document.add(new Paragraph("\n\n\n\n"));
// Information additionnelles
            PdfPTable tab2=new PdfPTable(2);
            tab2.setWidthPercentage(100);
            tab2.setWidths(new int[]{50,50});
            PdfPCell cell2=new PdfPCell();
            cell2.setBorder(PdfPCell.NO_BORDER);
            cell2.setHorizontalAlignment(0);

            cell2.setBackgroundColor(new BaseColor(0, 176, 240));

            cell2.setPhrase(new Phrase("Société à la capital de: "+seller.getSociale_capitale()+"\n\nAdresse GPS: "+seller.getGPS(),headerFont));
            tab2.addCell(cell2);

            cell2.setPhrase(new Phrase("Email: "+seller.getEmail()+"\n\nSite web: "+seller.getWebSite(),headerFont));
            tab2.addCell(cell2);


            document.add(tab2);

            document.close();


            return true; }
        catch (Exception e){
            System.out.println(e.toString());
                return false;
        }

    }
}
