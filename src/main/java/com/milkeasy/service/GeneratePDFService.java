package com.milkeasy.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.milkeasy.model.CustomDateRange;
import com.milkeasy.model.MilkTransaction;
import com.milkeasy.model.User;

@Service
public class GeneratePDFService {
	
	public void createMilkTransactionStatementPdf(User user, List<MilkTransaction> allMilkTransaction, CustomDateRange customDateRange) {
        String filePdf = "C:/Users/HP/Downloads/SamplePdfFile.pdf";
        try {
            PdfWriter writer = new PdfWriter(filePdf);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            
            document.add(new Paragraph("User Details").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Full Name: " + user.getFullName()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Mobile: " + user.getMobile()));
            document.add(new Paragraph("Address: " + user.getAddress()));

            if (customDateRange != null) {
                document.add(new Paragraph("Date Range").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph(""));
                document.add(new Paragraph("From: " + customDateRange.getCustomFromdate()));
                document.add(new Paragraph("To: " + customDateRange.getCustomTodate()));
                document.add(new Paragraph(""));
            }
            document.add(new Paragraph("Milk Transactions").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));
            if (allMilkTransaction != null && !allMilkTransaction.isEmpty()) {
                Table table = new Table(UnitValue.createPercentArray(new float[]{20, 20, 20, 20, 20, 20})).useAllAvailableWidth(); // 5 columns
                table.setTextAlignment(TextAlignment.CENTER);

             // Table headers
                table.addHeaderCell(new Cell().add(new Paragraph("Collection Date").setBold().setFontSize(12)));
                table.addHeaderCell(new Cell().add(new Paragraph("Collector Full Name").setBold().setFontSize(12)));
                table.addHeaderCell(new Cell().add(new Paragraph("Farmer Full Name").setBold().setFontSize(12)));
                table.addHeaderCell(new Cell().add(new Paragraph("Quantity").setBold().setFontSize(12)));
                table.addHeaderCell(new Cell().add(new Paragraph("Rate").setBold().setFontSize(12)));
                table.addHeaderCell(new Cell().add(new Paragraph("Amount").setBold().setFontSize(12)));

                for (MilkTransaction milkTransaction : allMilkTransaction) {
                    table.addCell(new Cell().add(new Paragraph(milkTransaction.getCollectionDate().toString()).setFontSize(10)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(milkTransaction.getCollectorFullName())).setFontSize(10)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(milkTransaction.getFarmerFullName())).setFontSize(10)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(milkTransaction.getQuantity())).setFontSize(10)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(milkTransaction.getRate())).setFontSize(10)));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(milkTransaction.getAmount())).setFontSize(10)));
                }

                document.add(table);
            } else {
                document.add(new Paragraph("No Milk Transactions found."));
            }
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
