package com.pdf.example.pdfDocument;

import com.itextpdf.text.DocumentException;
import com.pdf.example.pdfDocument.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PdfDocumentApplication {
	private static List<Product> showProductList(){
		return Arrays.asList(
				new Product("1","hp","pc","image.png","laptop",565,"pcs",1,"ETEY67"),
				new Product("2","hp","pc","image.png","laptop",565,"pcs",3,"ETEY67"),
				new Product("3","hp","pc","image.png","laptop",565,"pcs",5,"ETEY67")
		);
	}

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(PdfDocumentApplication.class, args);
		OrderDocumentPdf doc=new OrderDocumentPdf();
		FactureDocumentPdf fac=new FactureDocumentPdf();

		Order ord=new Order("1","123",0,0,0,showProductList(),"13/4/2021",120);

		Enterprise sel=new Enterprise("1","Jumia","www.jumia.com","jumia@e-commerce.dz","logo.png","044235412","Cité 123","234","325","222222255","53453","0205346546","335","lien_gps.com");
		Buyer bayer=new Buyer("1","Lachraf","NourElhouda","","06789536","Cité 300","","","");
		Facture facture=new Facture("1","23","13/5/2021","4/5/2021");
		Delivery delivery=new Delivery("1","34","12/6/2021","Cité Nakhil");

		DeliveryDocumentPdf deliveryDocumentPdf=new DeliveryDocumentPdf();
		Transaction transaction=new Transaction(1,"","","Carte ElDahabia");
		doc.Orderpdf(ord, bayer, sel,"./",transaction);
		fac.Facturepdf(ord,bayer,sel, transaction, facture,"./");
		deliveryDocumentPdf.Deliverypdf(ord,bayer,sel,transaction,delivery,"./");
	}
}
