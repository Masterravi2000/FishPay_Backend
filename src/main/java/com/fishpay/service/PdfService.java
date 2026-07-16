package com.fishpay.service;

import com.fishpay.entity.Invoice;
import com.fishpay.entity.InvoiceItem;
import com.fishpay.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Phrase;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfService {
    //dependency injection
    private final InvoiceItemRepository invoiceItemRepository;

    //constructor
    public PdfService (InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    public File generatePDF(Invoice invoice){
        //Fetch invoiceId from invoice
        Long invoiceId = invoice.getId();

        //Then Fetch invoice items using invoiceId
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(invoiceId);

        try {
            File tempDir = new File("temp");
            System.out.println("Temp exists: " + tempDir.exists());
            System.out.println("Temp path: " + tempDir.getAbsolutePath());
            System.out.println("Can write: " + tempDir.canWrite());

            // Create PDF file - File decides where the file will be stored and what it will be named
            File pdfFile = new File("temp/invoice_" + invoiceId + ".pdf");

            // Create document - here Document holds the PDF content in memory.
            Document document = new Document();

            //  Connect document with file - writes the Document content into the File
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            // Open document
            document.open();

            // Add sample text - using Paragraph one piece of content (text) added to the Document.
            document.add(new Paragraph("FishPay Invoice"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Payment Details"));
            document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Order Time: " + invoice.getOrderTime()));
            document.add(new Paragraph("Payment Method: " + invoice.getPaymentMethod()));
            document.add(new Paragraph("Payment Status: " + invoice.getPaymentStatus()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Product Details"));
            document.add(new Paragraph(" "));
            //Create the pdf table
            PdfPTable table = new PdfPTable(4);
            //Add Table headers
            table.addCell(new Phrase("Product"));
            table.addCell(new Phrase("Quantity"));
            table.addCell(new Phrase("Price"));
            table.addCell(new Phrase("Total"));

            //now Loop through all InvoiceItems for getting required details for the table
            for (InvoiceItem item : items) {
                table.addCell(new Phrase(item.getProductName()));
                table.addCell(new Phrase(String.valueOf(item.getQuantity())));
                table.addCell(new Phrase(item.getPrice().toString()));
                table.addCell(new Phrase(item.getTotalPrice().toString()));
            }

            //now add the table to pdf
            document.add(table);

            //Now add last two required details Delivery charges and total amount
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Delivery Charges: " + invoice.getDeliveryCharges()));
            document.add(new Paragraph("Total Amount: " + invoice.getTotalAmount()));

            // Close document
            document.close();

            return pdfFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
