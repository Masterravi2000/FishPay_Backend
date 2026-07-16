package com.fishpay.service;

import com.fishpay.entity.Invoice;
import com.fishpay.repository.InvoiceRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class InvoiceAsyncService {
    private final InvoiceRepository invoiceRepository;
    private final PdfService pdfService;
    private final CloudinaryService cloudinaryService;

    // Constructor
    public InvoiceAsyncService(InvoiceRepository invoiceRepository, PdfService pdfService,
            CloudinaryService cloudinaryService) {
        this.invoiceRepository = invoiceRepository;
        this.pdfService = pdfService;
        this.cloudinaryService = cloudinaryService;
    }

    @Async
    public void processInvoicePDFAsync(Long invoiceId) {
        // fetch invoice via its invoice id
        Invoice savedInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // now generate the invoice pdf and gets its url
        File pdf = pdfService.generatePDF(savedInvoice);

        try {
            // now save the generated PDF file on cloudinary
            String invoiceUrl = cloudinaryService.uploadPDF(pdf);

            // now after upload the pdf on cloudinary a url is returned now set that url in
            // invoiceUrl
            savedInvoice.setInvoiceUrl(invoiceUrl);

            // now save the invoice again for reflecting newly added data
            savedInvoice = invoiceRepository.save(savedInvoice);
        } finally {
            if (pdf.exists()) {
                pdf.delete();
            }
        }
    }
}
