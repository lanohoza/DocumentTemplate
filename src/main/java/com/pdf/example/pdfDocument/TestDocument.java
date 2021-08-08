package com.pdf.example.pdfDocument;

import com.itextpdf.text.DocumentException;
import com.pdf.example.pdfDocument.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class TestDocument {
    @InjectMocks
    OrderDocumentPdf comndpdf;
    @Mock
    Order order;
    @Mock
    Enterprise seller;
    @Mock
    Buyer buyer;
    @Mock
    Transaction transaction;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void Test_path_exist_CommandePdf() throws DocumentException, FileNotFoundException {
        String path_true = "./";
        boolean Doc = comndpdf.Orderpdf(order, buyer, seller, path_true, transaction);
        assertEquals(true, Doc);

    }

    @Test
    public void Test_CommandePdf_path_not_exist() throws DocumentException, FileNotFoundException {
        String path_false = "D://";
        boolean Doc = comndpdf.Orderpdf(order, buyer, seller, path_false, transaction);
        assertEquals(false, Doc);

    }
}


