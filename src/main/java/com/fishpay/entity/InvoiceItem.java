package com.fishpay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table( name = "invoice_items" )
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long invoiceId; // Links to Invoice
    private String productName;
    private Integer quantity;
    private BigDecimal price; // price per unit
    private BigDecimal totalPrice; // quantity * price

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //No-Args construtor
    public InvoiceItem () {
    }

    //Getter
    public Long getId () {
        return id;
    }

    //Getter
    public Long getInvoiceId () {
        return invoiceId;
    }
    //Setter
    public void setInvoiceId (Long invoiceId){
        this.invoiceId = invoiceId;
    }

    //Getter
    public String getProductName () {
        return productName;
    }
    //Setter
    public void setProductName (String productName) {
        this.productName = productName;
    }

    //Getter
    public Integer getQuantity () {
        return quantity;
    }
    //Setter
    public void setQuantity (Integer quantity){
        this.quantity = quantity;
    }

    //Getter
    public BigDecimal getPrice () {
        return price;
    }
    //Setter
    public void setPrice (BigDecimal price){
        this.price = price;
    }

    //Getter
    public BigDecimal getTotalPrice () {
        return totalPrice;
    }
    //Setter
    public void setTotalPrice (BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    //Getter
    public LocalDateTime getCreatedAt () {
        return createdAt;
    }
    //Getter
    public LocalDateTime getUpdatedAt () {
        return updatedAt;
    }
}
