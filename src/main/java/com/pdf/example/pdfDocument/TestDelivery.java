package com.pdf.example.pdfDocument;
import com.itextpdf.text.DocumentException;
import com.pdf.example.pdfDocument.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;


import static org.junit.Assert.assertEquals;

public class TestDelivery {
    @InjectMocks
    DeliveryDocumentPdf deliveryDocumentPdf;

    @Mock
    Order order;
    @Mock
    Enterprise seller;
    @Mock
    Buyer buyer;
    @Mock
    Transaction transaction;
    @Mock
    Delivery delivery;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void Test_path_exist_DeliveryPdf(){
        String path_true = "./";
        boolean Doc = deliveryDocumentPdf.Deliverypdf(order, buyer, seller, transaction, delivery, path_true);
        assertEquals(true, Doc);
    }

    @Test
    public void Test_DeliveryPdf_path_not_exist() throws DocumentException, FileNotFoundException {
        String path_false = "D://";
        boolean Doc = deliveryDocumentPdf.Deliverypdf(order, buyer, seller, transaction, delivery, path_false);
        assertEquals(false, Doc);
    }
}
