package com.pdf.example.pdfDocument;

import com.itextpdf.text.DocumentException;

import com.pdf.example.pdfDocument.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.io.FileNotFoundException;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestFacture {
    @InjectMocks
    FactureDocumentPdf factpdf;

    @Mock
    Order order;
    @Mock
    Enterprise seller;
    @Mock
    Buyer buyer;
    @Mock
    Transaction transaction;
    @Mock
    Facture facture;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void Test_path_exist_FacturePdf(){
        String path_true = "./";
        boolean Doc = factpdf.Facturepdf(order, buyer, seller, transaction, facture, path_true);
        assertEquals(true, Doc);

    }

    @Test
    public void Test_FacturePdf_path_not_exist() throws DocumentException, FileNotFoundException {
        String path_false = "D://";
        boolean Doc = factpdf.Facturepdf(order, buyer, seller, transaction, facture, path_false);
        assertEquals(false, Doc);

    }
}

