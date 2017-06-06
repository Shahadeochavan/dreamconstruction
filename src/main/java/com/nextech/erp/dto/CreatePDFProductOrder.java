package com.nextech.erp.dto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nextech.erp.model.Productorder;
import com.nextech.erp.service.ProductorderService;
import com.nextech.erp.service.ProductorderassociationService;

public class CreatePDFProductOrder {
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static float total = 0;

	/**
	 * @param args
	 */
	
	@Autowired
	ProductorderassociationService productorderassociationService;
	
	@Autowired
	ProductorderService productorderService;

	public Document createPDF(String file, Productorder productorder,List<ProductOrderData> productOrderDatas)
			throws Exception {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document, productorder,productOrderDatas);

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
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 20,Font.BOLD); 
		   Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
		  //specify column widths
		   float[] columnWidths = {5f};
		   //create PDF table with the given widths
		   PdfPTable table = new PdfPTable(columnWidths);
		   // set table width a percentage of the page width
		   PdfPCell cell =  new PdfPCell();
		   
		 /*  Rectangle rect= new Rectangle(36, 820, 564, 710);
	        rect.setBorder(2);
	        rect.setBorder(Rectangle.BOX);
	        rect.setBorderWidth(1);
	        rect.setBorderColor(BaseColor.BLACK);
	        document.add(rect);*/
		   creteEmptyLine(preface, 2);
		   document.add(preface);
		   PdfPTable table1 = new PdfPTable(1);
		     table1.setWidthPercentage(100);
		     table1.addCell(getCell1("E.K.ELECTRONICS PVT.LTD", PdfPCell.ALIGN_CENTER,bf12));
		     document.add(table1);
		     
		     PdfPTable table2 = new PdfPTable(1);
		     table2.setWidthPercentage(100);
		     table2.addCell(getCell("E-64 MIDC Industrial,Ranjangon Tal Shirur Dist pune-412220", PdfPCell.ALIGN_CENTER));
		     document.add(table2);
		     
		     PdfPTable table3 = new PdfPTable(1);
		     table3.setWidthPercentage(100);
		     table3.addCell(getCell("Email:sachi@eksgpl.com/purchase@eksgpl.com", PdfPCell.ALIGN_CENTER));
		     document.add(table3);
		     
		     PdfPTable table4 = new PdfPTable(1);
		     table4.setWidthPercentage(100);
		     table4.addCell(getCell("PURCHASE ORDER", PdfPCell.ALIGN_CENTER));
		     document.add(table4);
		     
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Product Order Invoice Date :"
				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);


	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void createTable(Document document, Productorder productorder,List<ProductOrderData> productOrderDatas)
			throws Exception {

		  Paragraph paragraph = new Paragraph();
		 creteEmptyLine(paragraph, 2);
		 document.add(paragraph);
		  DecimalFormat df = new DecimalFormat("0.00");
		  Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
		  //specify column widths
		   float[] columnWidths = {1.5f, 1.5f, 1.5f, 1.5f};
		   //create PDF table with the given widths
		   PdfPTable table = new PdfPTable(columnWidths);
		   // set table width a percentage of the page width
		   table.setWidthPercentage(100f);

		   //insert column headings
		   insertCell(table, "Product Name", Element.ALIGN_RIGHT, 1, bfBold12);
		   insertCell(table, "Quantity", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Rate", Element.ALIGN_LEFT, 1, bfBold12);
		   insertCell(table, "Amount", Element.ALIGN_LEFT, 1, bfBold12);
		   table.setHeaderRows(1);

     for (ProductOrderData productOrderData : productOrderDatas) {
	  insertCell(table,productOrderData.getProductName() , Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table,(Long.toString(productOrderData.getQuantity())), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, (Long.toString(productOrderData.getRate())), Element.ALIGN_CENTER, 1, bf12);
	    insertCell(table, (Long.toString(productOrderData.getAmount())), Element.ALIGN_CENTER, 1, bf12);
	    total = total+productOrderData.getAmount();
    }
     insertCell(table, "Total", Element.ALIGN_CENTER, 3, bfBold12);
     insertCell(table, df.format(total), Element.ALIGN_CENTER, 1, bfBold12);
     document.add(table);
     Font bf123 = new Font(FontFamily.TIMES_ROMAN, 12,Font.BOLD); 
     document.add(new Paragraph("Terms & Conditions", bf123));
     document.add(new Paragraph("Payment Terms                        60 Days on receipt of matrial", bf12));
     document.add(new Paragraph("Inspection                           At our works after receipt of material", bf12));
     document.add(new Paragraph("Sale Tax                             As applicable", bf12));
     document.add(new Paragraph("Excise Duty                          As applicable", bf12));
     document.add(new Paragraph("Delivery                             At our Ranjangon factory", bf12));
     document.add(new Paragraph("freight                              Nil", bf12));
     document.add(new Paragraph("Quality                              Supply material of high quality only to avoid rejection", bf12));
     document.add(new Paragraph("Note", bf123));
     document.add(new Paragraph("1. Please send duplicate bill mentioned with our P.O and item code", bf12));
     document.add(new Paragraph("2. Bill & challan must be issued in favor of M/s EK Electronics pvt.ltd. at above address", bf12));
     document.add(new Paragraph("3. Please make sure all material should be rohs compliance ", bf12));
     document.add(paragraph);
     
     PdfPTable table1 = new PdfPTable(1);
     table1.setWidthPercentage(100);
     table1.addCell(getCell("For EK Electronics Pvt.Ltd", PdfPCell.ALIGN_RIGHT));
     document.add(table1);
     
     creteEmptyLine(paragraph, 1);
     document.add(paragraph);
     
     PdfPTable table2 = new PdfPTable(1);
     table2.setWidthPercentage(100);
     table2.addCell(getCell("Authorised Signatory", PdfPCell.ALIGN_RIGHT));
     document.add(table2);
    
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
}	
