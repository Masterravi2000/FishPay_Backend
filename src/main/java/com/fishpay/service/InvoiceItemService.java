package com.fishpay.service;

import com.fishpay.dto.ProductDto;
import com.fishpay.entity.InvoiceItem;
import com.fishpay.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvoiceItemService {
    //connect this service with its Db Repository
    private InvoiceItemRepository invoiceItemRepository;

    //now create a constructor for assigning invoiceRepository
    public InvoiceItemService (InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    public void saveInvoiceItems(Long invoiceId, List<ProductDto> products) {
        //extract from the products via a for each loop
        String productName;
        Integer quantity;
        BigDecimal price;
        for(ProductDto product : products){
            productName = product.getProductName();
            quantity = product.getQuantity();
            price = product.getPrice();

            //create an InvoiceItem object and populate it
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(invoiceId);
            invoiceItem.setProductName(productName);
            invoiceItem.setQuantity(quantity);
            invoiceItem.setPrice(price);
            invoiceItem.setTotalPrice(price.multiply(BigDecimal.valueOf(quantity)));

            //now save this invoiceItem object into invoice_items Table
            invoiceItemRepository.save(invoiceItem);
        }
    }
}

