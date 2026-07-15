# FishPay_Backend

## 💳 Production-Oriented Payment Management Infrastructure

FishPay is a production-oriented payment management infrastructure built using **Java, Spring Boot, PostgreSQL, Razorpay, and Docker** that enables businesses to integrate a complete payment management system without investing significant development time or dealing with the complexities involved in building one from scratch.

The system manages the complete payment lifecycle, including payment processing, payment verification, webhook reconciliation, invoice generation, refund management, transaction history, and analytics through reusable REST APIs.

Although FishPay currently integrates with **Razorpay**, its modular architecture allows future support for multiple payment gateways such as **Stripe, PayPal, PayU**, and others with minimal architectural changes.

---

# ✨ Features

### 💳 Payment
- Order Creation
- Payment Verification
- Payment History
- Transaction Tracking

### 📄 Invoice
- Automatic Invoice Generation
- Invoice History
- PDF Download

### 💰 Refund
- Refund Processing
- Refund Status
- Refund History

### 🔔 Webhooks
- Payment Reconciliation
- Refund Synchronization

### ⚡ Performance
- Async Invoice Generation
- Fast Payment Confirmation
- Backend Pagination

### 🐳 Infrastructure
- Dockerized Backend
- PostgreSQL
- RESTful APIs

---

# ⭐ Engineering Problems Solved

Unlike a simple payment gateway integration, FishPay addresses several real-world backend engineering challenges commonly encountered in payment systems.

---

## ⚡ 1. Reduced Payment Response Time by ~75%

### Problem

Initially, the payment verification API performed every operation synchronously.

```
Verify Signature
      ↓
Save Payment
      ↓
Generate Invoice PDF
      ↓
Upload to Cloudinary
      ↓
Save Invoice URL
      ↓
Return Response
```

Since PDF generation and cloud upload are expensive operations, the client had to wait approximately **3 seconds** before receiving payment confirmation.

---

### Solution

Invoice generation was moved into an asynchronous background task.

```
Verify Signature
      ↓
Save Payment
      ↓
Return Success Response
      ↓
──────── Background Thread ────────
Generate Invoice
      ↓
Upload to Cloudinary
      ↓
Update invoiceUrl
```

### Result

✅ Payment confirmation reduced from approximately **3 seconds** to **under 1 second** while maintaining eventual consistency.

---

## 🔄 2. Webhook-Based Event Reconciliation

### Problem

Frontend responses cannot always be trusted as the single source of truth because network failures or application crashes may occur after payment completion.

---

### Solution

Implemented secure Razorpay Webhook integration that verifies webhook signatures and automatically reconciles payment and refund events.

Supported events include:

- payment.captured
- payment.failed
- refund.processed
- order.paid

This guarantees database consistency even if the frontend never reports the payment outcome.

---

## ⏳ 3. Race Condition Handling

### Problem

Sometimes Razorpay sends the webhook before the payment record is inserted into PostgreSQL.

Without handling this race condition, webhook processing would fail.

---

### Solution

Implemented retry-based reconciliation logic that repeatedly searches for the payment before processing the webhook.

This ensures eventual consistency between asynchronous systems.

---

## 📦 4. Complete Refund Lifecycle Synchronization

Instead of only creating refund records, FishPay synchronizes refund status across multiple entities.

```
Refund Created
      ↓
PENDING
      ↓
PROCESSING
      ↓
PROCESSED
      ↓
Payment.refunded = true
```

This keeps both Payment and Refund records synchronized automatically.

---

## 📑 5. Background Invoice Pipeline

Invoice generation was intentionally separated from payment confirmation.

Background pipeline:

```
Payment Success
      ↓
Async Task
      ↓
Generate PDF
      ↓
Upload to Cloudinary
      ↓
Persist invoiceUrl
```

This significantly improves checkout experience while preserving complete invoice generation.

---

## 📚 6. Efficient Pagination

Instead of loading entire datasets, FishPay uses backend pagination for:

- Payments
- Invoices
- Refunds

allowing efficient handling of large transaction histories.

---

## 🐳 7. Containerized Backend

The backend is fully containerized using Docker, making deployments reproducible across development, staging, and production environments.

---


```
FishPay_Backend/
│
├── src/main/java/com/fishpay
│
├── config/
│   ├── RazorpayConfig.java
│   ├── SecurityConfig.java
│   ├── CloudinaryConfig.java
│   └── AsyncConfig.java
│
├── controllers/
│   ├── PaymentController.java
│   ├── InvoiceController.java
│   ├── RefundController.java
│   ├── WebhookController.java
│   └── HealthController.java
│
├── service/
│   ├── PaymentService.java
│   ├── InvoiceService.java
│   ├── RefundService.java
│   ├── WebhookService.java
│   ├── AsyncInvoiceService.java
│   └── CloudinaryService.java
│
├── repository/
│   ├── PaymentRepository.java
│   ├── InvoiceRepository.java
│   ├── InvoiceItemRepository.java
│   └── RefundRepository.java
│
├── entity/
│   ├── Payment.java
│   ├── Invoice.java
│   ├── InvoiceItem.java
│   └── Refund.java
│
├── dto/
│   ├── CreateOrderRequest.java
│   ├── VerifyPaymentRequest.java
│   ├── VerifyPaymentResponse.java
│   ├── InvoiceHistoryResponse.java
│   ├── RefundRequest.java
│   ├── RefundResponse.java
│   ├── RefundHistoryResponse.java
│   ├── PaymentHistoryResponse.java
│   └── ...
│
├── util/
│   ├── RazorpaySignatureUtil.java
│   ├── InvoiceGenerator.java
│   ├── RefundStatus.java
│   └── PaymentStatus.java
│
├── exception/
│
├── FishpayApplication.java
│
├── src/main/resources/
│   ├── application.yml
│   └── ...
│
├── Dockerfile
├── pom.xml
└── README.md
```


## 🧱 8. Modular Spring Boot Architecture

The backend follows production-standard layered architecture.

```
Controller
      ↓
Service
      ↓
Repository
      ↓
Hibernate / JPA
      ↓
PostgreSQL
```

This separation improves maintainability, scalability, testing, and code organization.

---

# 🏗️ High-Level Architecture

```
Merchant Application
        │
        ▼
 REST Controllers
        │
        ▼
 Service Layer
        │
 ┌──────┴──────────┐
 ▼                 ▼
PostgreSQL     Razorpay APIs
                     │
                     ▼
                Razorpay Webhooks
                     │
                     ▼
             Webhook Reconciliation
                     │
                     ▼
             Payment Synchronization
                     │
                     ▼
           Invoice / Refund Modules
```

---

# 🛠 Backend Tech Stack

### Core Backend

- Java 24
- Spring Boot 3.5.x
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate ORM
- Maven

### Database

- PostgreSQL

### External Services

- Razorpay Java SDK
- Cloudinary

### Infrastructure

- Docker
- REST APIs
- Environment Variables

### Supporting Libraries

- Jackson
- BigDecimal
- Pageable
- JPA Repository
- Hibernate
- Async Processing (`@Async`)