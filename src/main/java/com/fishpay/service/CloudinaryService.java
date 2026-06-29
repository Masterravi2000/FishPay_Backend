package com.fishpay.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class CloudinaryService {
    //cloudinary bean dependency injection
    private final Cloudinary cloudinary;

    //constructor
    public CloudinaryService (Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    //function for uploading the pdf on cloudinary
    public String uploadPDF(File pdfFile){
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    pdfFile,
                    ObjectUtils.asMap(
                            "resource_type", "raw",
                            "folder", "fishpay/invoices"
                    )
            );

            return uploadResult.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload PDF to Cloudinary", e);
        }
    }
}
