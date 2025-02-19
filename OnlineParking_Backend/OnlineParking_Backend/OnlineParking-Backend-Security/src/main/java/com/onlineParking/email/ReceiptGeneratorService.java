package com.onlineParking.email;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ReceiptGeneratorService {
    public byte[] generateReceipt(String firstName, String lastName, String email, String mobileNumber, String title,
			String description, double totalAmount, String formattedBookingDate, String[][] userDetails) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {
        	 // Title
            document.add(new Paragraph("Parking Booking Receipt").setBold().setFontSize(16));

            // Customer Information
            document.add(new Paragraph("Name : " + firstName + " " + lastName));
            document.add(new Paragraph("Email: " + email));
            document.add(new Paragraph("Mobile Number: " + mobileNumber));

            // Tour Details
            document.add(new Paragraph("Slot No.: " + title));
            document.add(new Paragraph("Description: " + description));
            document.add(new Paragraph("Amount Paid: ₹" + totalAmount));
            document.add(new Paragraph("Booking Date: " + formattedBookingDate));


            // Closing message
            document.add(new Paragraph("\nThank you for booking with us!"));

            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



	
}
