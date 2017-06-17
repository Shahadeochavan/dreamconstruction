package com.nextech.erp.dto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nextech.erp.service.BomService;

public class CreatePdfForBomProduct {

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static float total = 0;

	/**
	 * @param args
	 */
	
	@Autowired
	BomService bomService;

	public Document createPDF(String file, List<BomRMVendorModel> bomRMVendorModels)
			throws Exception {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document, bomRMVendorModels);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Java Honk");
		document.addCreator("Java Honk");
	}

	private  void addTitlePage(Document document)
			throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("BOM INVOICE", TIME_ROMAN));
		
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 20,Font.BOLD); 
		   Font bf112 = new Font(FontFamily.TIMES_ROMAN, 15,Font.BOLD); 
		   Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		   
		  PdfPTable table00 = new PdfPTable(1);
		   table00.setWidthPercentage(100);
		   PdfPTable table1 = new PdfPTable(1);
		     table1.setWidthPercentage(100);
		     table1.addCell(getCell1("E.K.ELECTRONICS PVT.LTD", PdfPCell.ALIGN_CENTER,bf12));
		     table1.addCell(getCell("E-64 MIDC Industrial,Ranjangon Tal Shirur Dist pune-412220", PdfPCell.ALIGN_CENTER));
		     table1.addCell(getCell("Email:sachi@eksgpl.com/purchase@eksgpl.com", PdfPCell.ALIGN_CENTER));
		     table1.addCell(getCell1("BOM", PdfPCell.ALIGN_CENTER,bf112));
		     table00.addCell(table1);
		     document.add(table00);

/*		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("BOM Invoice Date :"
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);*/

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void createTable(Document document, List<BomRMVendorModel> bomRMVendorModels)
			throws Exception {
		
		Paragraph paragraph = new Paragraph();
		DecimalFormat df = new DecimalFormat("0.00");

		  Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
		  //specify column widths
		   float[] columnWidths = {1.5f, 1.5f, 1.5f, 1.5f,1.5f,1.5f};
		   //create PDF table with the given widths
		   PdfPTable table = new PdfPTable(columnWidths);
		   // set table width a percentage of the page width
		   table.setWidthPercentage(100f);

		   //insert column headings
		   insertCell(table, "RM NAME", Element.ALIGN_RIGHT, 1, bfBold12);
		   insertCell(table, "VENDOR NAME", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "PRODUCT NAME", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "PRICE PER UNIT", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "QUANTITY", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "AMOUNT", Element.ALIGN_RIGHT, 1, bfBold12);
		   table.setHeaderRows(1);
	
		 //  insertCell(table, "", Element.ALIGN_LEFT, 6, bfBold12);
		   //create section heading by cell merging
		 //  insertCell(table, "BOM DETAILS ...", Element.ALIGN_LEFT, 6, bfBold12);

     for (BomRMVendorModel bomRMVendorModel : bomRMVendorModels) {
	  insertCell(table,bomRMVendorModel.getRmName() , Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, bomRMVendorModel.getVendorName(), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, bomRMVendorModel.getProductName(), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, (Float.toString(bomRMVendorModel.getPricePerUnit())), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, (Long.toString(bomRMVendorModel.getQuantity())), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, (Float.toString(bomRMVendorModel.getAmount())), Element.ALIGN_CENTER, 1, bf12);
	    total = total+bomRMVendorModel.getAmount();
	  
    }
     insertCell(table, "Total", Element.ALIGN_CENTER, 5, bfBold12);
     insertCell(table, df.format(total), Element.ALIGN_CENTER, 1, bfBold12);
     document.add(table);
	}

	 private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
	  
	  //create a new cell with the specified Text and Font
	  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
	  //set the cell alignment
	  cell.setHorizontalAlignment(align);
	  //set the cell column span in case you want to merge two or more cells
	  cell.setColspan(colspan);
	  //in case there is no text and you wan to create an empty row
	  if(text.trim().equalsIgnoreCase("")){
	   cell.setMinimumHeight(15f);
	  }
	  //add the call to the table
	  table.addCell(cell);
	  
	 }
	 
	 public PdfPCell getCell(String text, int alignment) {
		    PdfPCell cell = new PdfPCell(new Phrase(text));
		    cell.setPadding(0);
		    cell.setExtraParagraphSpace(1);
		    cell.setHorizontalAlignment(alignment);
		    cell.setBorder(PdfPCell.NO_BORDER);
		    return cell;
		}
	 
	 public PdfPCell getCell1(String text, int alignment,Font bf12) {
		    PdfPCell cell = new PdfPCell(new Phrase(text,bf12));
		    cell.setPadding(0);
		    cell.setHorizontalAlignment(alignment);
		    cell.setBorder(PdfPCell.NO_BORDER);
		    return cell;
		}
	 public PdfPCell getCell2(String text, int alignment,Font font) {
		    PdfPCell cell = new PdfPCell(new Phrase(text));
		    cell.setPadding(0);
		    cell.setExtraParagraphSpace(1);
		    cell.setVerticalAlignment(alignment);
		    cell.setBorder(PdfPCell.NO_BORDER);
		    return cell;
		}
}
