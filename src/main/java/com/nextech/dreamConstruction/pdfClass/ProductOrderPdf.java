package com.nextech.dreamConstruction.pdfClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.dto.ProductOrderDTO;
import com.nextech.dreamConstruction.dto.ProductOrderPDFData;
import com.nextech.dreamConstruction.model.Contractor;

public class ProductOrderPdf {

	public static String answer = "";
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	public  double SUB_TOTAL = 0;
	public double igstTotal =0;
	public double cgstTotal14 = 0;
	public double cgstTotal9 = 0;
	public double cgstTotal6 = 0;
	public double cgstTotal2 = 0;
	public double sgstTotal14 =0;
	public double sgstTotal9 =0;
	public double sgstTotal6 =0;
	public double sgstTotal2 =0;
	public String cgst ="";
	public String sgst ="";
	public String subtoatl ="";
	/**
	 * @param args
	 */
  

	public  Document createPDF(String file,List<ProductOrderPDFData> productOrderPDFDatas,ProductOrderDTO productOrderDTO,Contractor contractor) throws Exception {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document,contractor,productOrderDTO);

		  createTable(document,productOrderPDFDatas, productOrderDTO);

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

	private  void addTitlePage(Document document,Contractor contractor,ProductOrderDTO productOrderDTO)
			throws DocumentException, MalformedURLException, IOException {


		Paragraph paragraph = new Paragraph();
		paragraph.add("TAX INVOICE");
		paragraph.setAlignment(Element.ALIGN_CENTER);
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10,Font.BOLD); 
		   Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
		  
		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    float[] columnWidthImageInvoice = {25f,50f,25f,25f};
		     PdfPTable imageInvoiceDateTable = new PdfPTable(columnWidthImageInvoice);
		     imageInvoiceDateTable.setWidthPercentage(100f);
		     
		     PdfPTable imageTable = new PdfPTable(1);
		    // Image img = Image.getInstance((getClass().getResource("/images/logo1.png")));
		   //  imageTable.addCell(img);
		   
		     PdfPTable fromTabel = new PdfPTable(1);
		     fromTabel.addCell(getCell("FROM :", PdfPCell.ALIGN_LEFT,bf12));
		     fromTabel.addCell(getCell("ALTAN ENTERPRISES", PdfPCell.ALIGN_LEFT,bf12));
		     fromTabel.addCell(getCell("At-Shinde,Post:Vasuli Chakan Industrial Area Phase2,Tal-khed Pune 410501", PdfPCell.ALIGN_LEFT,font3));
		     fromTabel.addCell(getCell("Email : contact@altanservices.com", PdfPCell.ALIGN_LEFT,font3));
		     fromTabel.addCell(getCell("Contact no : 9850799344/7030900294", PdfPCell.ALIGN_LEFT,font3));
		     fromTabel.addCell(getCell("GSTIN NO : 27ABDFA6918M1Z3", PdfPCell.ALIGN_LEFT,font3));
		     fromTabel.addCell(getCell("TIN NO : 27431146979V", PdfPCell.ALIGN_LEFT,font3));
		     fromTabel.addCell(getCell("PAN NO: ABDFA6918M", PdfPCell.ALIGN_LEFT,font3));
		     
			  
			  PdfPTable invoiceTableData = new PdfPTable(1);
			  invoiceTableData.addCell(getCell("INVOICE NO :"+productOrderDTO.getInvoiceNo(), PdfPCell.ALIGN_LEFT,bf12));
			  invoiceTableData.addCell(getCell("CHALLAN NO :             ",PdfPCell.ALIGN_LEFT,bf12));
			  invoiceTableData.addCell(getCell("",PdfPCell.ALIGN_LEFT,bf12));
			  invoiceTableData.addCell(getCell("P.O.NO   :             ",PdfPCell.ALIGN_LEFT,bf12));
			  invoiceTableData.addCell(getCell("SAC CODE   :             ",PdfPCell.ALIGN_LEFT,bf12));
			     
			   PdfPTable dateTableData = new PdfPTable(1);
			   dateTableData.addCell(getCell("DATE :"+simpleDateFormat.format(new Date()), PdfPCell.ALIGN_LEFT,bf12));
			   dateTableData.addCell(getCell("DATE :", PdfPCell.ALIGN_LEFT,bf12));
			   dateTableData.addCell(getCell("PLACE OF SUPPLY :PUNE Maharashtra",PdfPCell.ALIGN_LEFT,bf12));
		
			   imageInvoiceDateTable.addCell(imageTable);
			   imageInvoiceDateTable.addCell(fromTabel);
		       imageInvoiceDateTable.addCell(invoiceTableData);
		       imageInvoiceDateTable.addCell(dateTableData);
		       document.add(imageInvoiceDateTable);
		     
		     
		     float[] fromConsigneeColumWidth = {50f,50f};
		     PdfPTable fromConsigneeTable = new PdfPTable(fromConsigneeColumWidth);
		     fromConsigneeTable.setWidthPercentage(100f);
		     
		    
		     PdfPTable reciverTable = new PdfPTable(1);
		     reciverTable.addCell(getCell("BUYER)", PdfPCell.ALIGN_LEFT,bf12));
		     reciverTable.addCell(getCell("Name :"+contractor.getFirstName(), PdfPCell.ALIGN_LEFT,bf12));
		     reciverTable.addCell(getCell("Address  :"+contractor.getAddress(), PdfPCell.ALIGN_LEFT,font3));
		     
		     PdfPTable taxTable = new PdfPTable(1);
		     taxTable.addCell(getCell("GSTIN NO. : "+"27AABCH7371R1ZV", PdfPCell.ALIGN_LEFT,font3));
		     taxTable.addCell(getCell("TIN NO. : ", PdfPCell.ALIGN_LEFT,font3));
		     taxTable.addCell(getCell("PAN NO. : ", PdfPCell.ALIGN_LEFT,font3));
		   
		     fromConsigneeTable.addCell(reciverTable);
		     fromConsigneeTable.addCell(taxTable);
		     document.add(fromConsigneeTable);
	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private  void createTable(Document document,List<ProductOrderPDFData> productOrderDatas,ProductOrderDTO productOrderDTO) throws Exception {
              DecimalFormat df = new DecimalFormat("0.0");
			  Font bf123 = new Font(FontFamily.TIMES_ROMAN, 10,Font.BOLD); 
			  Font bfBold = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0));
			   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 10); 
			   
			   Font bf1 = new Font(FontFamily.TIMES_ROMAN, 10); 
			   float[] columnWidths = {3.5f, 3.5f,3.5f};
			   PdfPTable table = new PdfPTable(columnWidths);
			   table.setWidthPercentage(100f);

			   insertCell(table, "Description of Goods", Element.ALIGN_RIGHT, 1, bf123);
			   insertCell(table, "HSN Code", Element.ALIGN_LEFT, 1, bf123);
			   insertCell(table, "Quantity", Element.ALIGN_LEFT, 1, bf123);
			  // insertCell(table, "Rate", Element.ALIGN_LEFT, 1, bf123);
			  // insertCell(table, "T/GST/%", Element.ALIGN_LEFT, 1, bf123);
			 //  insertCell(table, "Amount", Element.ALIGN_LEFT, 1, bf123);
			   table.setHeaderRows(1);

	     for (ProductOrderPDFData productOrderData : productOrderDatas) {
		     insertCell(table,productOrderData.getProductPartNumber() , Element.ALIGN_LEFT, 1, bf12);
		     insertCell(table,productOrderData.getHsnCode() , Element.ALIGN_LEFT, 1, bf12);
		      insertCell(table,(Long.toString(productOrderData.getQuantity())), Element.ALIGN_LEFT, 1, bf12);
		    //  insertCell(table, (Float.toString(productOrderData.getPricePerUnit())), Element.ALIGN_LEFT, 1, bf12);
		      String  gst = String.valueOf(productOrderData.getGst())+"%";
		      insertCell(table,gst , Element.ALIGN_LEFT, 1, bf12);
		       float amout = 0;
		      amout = productOrderData.getQuantity()*productOrderData.getPricePerUnit();
		      double d2 =amout;
			   String  amountToatl= String.format("%.02f", d2);
		    //  insertCell(table, amountToatl, Element.ALIGN_RIGHT, 1, bf12);
		      SUB_TOTAL = SUB_TOTAL+amout;
		      double d3 =SUB_TOTAL;
		       subtoatl= String.format("%.02f", d3);
		       float  cgst =0;
		       if(productOrderData.getGst()==28){
		    	   cgst = 14*amout;
		    	   cgstTotal14 = cgstTotal14+cgst/100;
		    	   sgstTotal14 = cgstTotal14;
		       }
		       if(productOrderData.getGst()==18){
		    	   cgst = 9*amout;
		    	   cgstTotal9 = cgstTotal9+cgst/100;
		    	   sgstTotal9 = cgstTotal9;
		       }
		       if(productOrderData.getGst()==12){
		    	   cgst = 6*amout;
		    	   cgstTotal6 = cgstTotal6+cgst/100;
		    	   sgstTotal6 = cgstTotal6;
		       }
		       if(productOrderData.getGst()==5){
		    	   cgst = (float) (2.5*amout);
		    	   cgstTotal2 = cgstTotal2+cgst/100;
		    	   sgstTotal2 = cgstTotal2;
		       }
	    }
	     document.add(table);
	     
	/*   //  insertCell(table, "BASE VALUE", Element.ALIGN_RIGHT, 5, bf123);
	     //insertCell(table, subtoatl, Element.ALIGN_RIGHT, 1, bf123);
	     if(cgstTotal14>0){
	    	 double d2 =cgstTotal14;
		   String  cgst= String.format("%.02f", d2);
	    	 insertCell(table, "CGST-14 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, cgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(sgstTotal14>0){
	    	 double d2 =sgstTotal14;
			   String  sgst= String.format("%.02f", d2);
	    	 insertCell(table, "SGST-14 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, sgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(cgstTotal9>0){
	    	 double d2 =cgstTotal9;
			   String  cgst= String.format("%.02f", d2);
	    	 insertCell(table, "CGST-9 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, cgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(sgstTotal9>0){
	    	 double d2 =sgstTotal9;
			   String  sgst= String.format("%.02f", d2);
	    	 insertCell(table, "SGST-9 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, sgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(cgstTotal6>0){
	    	 double d2 =cgstTotal6;
			   String  cgst= String.format("%.02f", d2);
	    	 insertCell(table, "CGST-6 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, cgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(sgstTotal6>0){
	    	 double d2 =sgstTotal6;
			   String  sgst= String.format("%.02f", d2);
	    	 insertCell(table, "SGST-6 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table,sgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(cgstTotal2>0){
	    	 double d2 =cgstTotal2;
			   String  cgst= String.format("%.02f", d2);
	    	 insertCell(table, "CGST-2.5 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, cgst, Element.ALIGN_RIGHT, 1, bf123);
	     }
	     if(sgstTotal2>0){
	    	 double d2 =sgstTotal2;
			  String  sgst= String.format("%.02f", d2);
	    	 insertCell(table, "SGST-2.5 %", Element.ALIGN_RIGHT, 5, bf123);
		     insertCell(table, sgst, Element.ALIGN_RIGHT, 1, bf123);
	     }*/
	     
	   /*  insertCell(table, "GRAND TOTAL Rs", Element.ALIGN_RIGHT, 5, bf123);
	     int totalPrice= (int) (SUB_TOTAL+cgstTotal14+cgstTotal2+cgstTotal9+cgstTotal6+sgstTotal14+sgstTotal9+sgstTotal6+sgstTotal2);
	     double d3 =totalPrice;
		 String    finalAns= String.format("%.02f", d3);
	     insertCell(table, finalAns, Element.ALIGN_RIGHT, 1, bf123);
	     document.add(table);
			if(totalPrice <= 0)   {                
				System.out.println("Enter numbers greater than 0");
			} else {

	
		 String  totalTaxInWord = calculationInWord(totalPrice);
		 float[] invoiceValueColumnwidth = {30f,50f,20f};
	     PdfPTable invoiceValueTable = new PdfPTable(invoiceValueColumnwidth);
	     invoiceValueTable.setWidthPercentage(100);
	     
	     PdfPTable valueTable = new PdfPTable(1);
	     valueTable.addCell(getCell("GRAND TOTAL IN WORDS:", PdfPCell.ALIGN_LEFT,bf123));
	     
	     PdfPTable wordTable =  new PdfPTable(1);
	     wordTable.addCell(getCell("Indian Rupees :"+totalTaxInWord, PdfPCell.ALIGN_LEFT,bf123));
	     
	     PdfPTable valueBlankTable= new PdfPTable(1);
	     
	     invoiceValueTable.addCell(valueTable);
	     invoiceValueTable.addCell(wordTable);
	     invoiceValueTable.addCell(valueBlankTable);
	     document.add(invoiceValueTable);
			}   */
	     
	     float[] declrationWidth = {100f};
	     PdfPTable declarationMainTable = new PdfPTable(declrationWidth);
	     declarationMainTable.setWidthPercentage(100f);
	     
	     PdfPTable declarationTable = new PdfPTable(1);
	     declarationTable.addCell(getCell("I/We certify that our registration certificate under the GST Act 2017 is in force on the date on which the supply"
	     		+ "of goods services specified in this tax invoice  is made by me/us & the transaction of supply covered by this tax invoice had been"
	     		+ " effected by me/us and it shall be accounted for in the turnover of supplies while filling of return & thye due tax if any correct & the amount indicated represents the price  actually charged and that there is no flow of additional consideration di"
	     		+ "directly or indirectly from the buyer.", PdfPCell.ALIGN_LEFT,bf1));
	     declarationMainTable.addCell(declarationTable);
	     document.add(declarationMainTable);
	     
	     PdfPTable table11 = new PdfPTable(1);
	     table11.setWidthPercentage(100);
	     
	    PdfPTable table1 = new PdfPTable(1);
	     table1.setWidthPercentage(100);
	     table1.addCell(getCell12("For Altan Enterprise", PdfPCell.ALIGN_RIGHT,bf123));
	     table1.addCell(getCell12("Authorised Signature", PdfPCell.ALIGN_RIGHT,bf123));
	     table1.addCell(getCell01("Reciver Signature & Stamp", PdfPCell.ALIGN_LEFT,bf123));
	     table11.addCell(table1);
	     document.add(table11);

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
	 public PdfPCell getCell(String text, int alignment,Font bf12) {
		    PdfPCell cell = new PdfPCell(new Phrase(text,bf12));
		    cell.setPaddingLeft(10f);
		    cell.setExtraParagraphSpace(1);
		    cell.setVerticalAlignment(alignment);
		    cell.setBorder(PdfPCell.NO_BORDER);
		    return cell;
		}
	 
	 public PdfPCell getCel1(String text, int alignment,Font bf12) {
		    PdfPCell cell = new PdfPCell(new Phrase(text,bf12));
		    cell.setVerticalAlignment(alignment);
		    cell.setBorder(PdfPCell.NO_BORDER);
		    cell.setExtraParagraphSpace(10);
		    return cell;
		}
	 
		public void pw(int n,String ch)
		{
			String  one[]={" "," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"," Ten"," Eleven"," Twelve"," Thirteen"," Fourteen","Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};
			String ten[]={" "," "," Twenty"," Thirty"," Forty"," Fifty"," Sixty","Seventy"," Eighty"," Ninety"};

			if(n > 19) {
				answer += ten[n/10]+" "+one[n%10];
			} else { 
				String S=one[n];
				answer += S;
			}

			if(n > 0) {
				answer += ch;
			}
		}
		public String calculationInWord(int total){
			answer = new String();
			if(total <= 0)   {                
				System.out.println("Enter numbers greater than 0");
			} else {
				ProductOrderPdf a = new ProductOrderPdf();
				a.pw((total/1000000000)," Hundred");
				a.pw((total/10000000)%100," Crore");
				a.pw(((total/100000)%100)," Lakh");
				a.pw(((total/1000)%100)," Thousand");
				a.pw(((total/100)%10)," Hundred");
				a.pw((total%100)," ");
				answer = answer.trim();
				System.out.println("Final Answer : " +answer);
		}
			return answer;
		}
		 public PdfPCell getCell12(String text, int alignment,Font font) {
			    PdfPCell cell = new PdfPCell(new Phrase(text,font));
			    cell.setPaddingLeft(5f);
			    cell.setExtraParagraphSpace(20);
			    cell.setHorizontalAlignment(alignment);
			    cell.setBorder(PdfPCell.NO_BORDER);
			    return cell;
			}
		 
		 public PdfPCell getCell01(String text, int alignment,Font font) {
			    PdfPCell cell = new PdfPCell(new Phrase(text,font));
			    cell.setPaddingTop(-30f);
			    cell.setPaddingLeft(10f);
			    cell.setHorizontalAlignment(alignment);
			    cell.setBorder(PdfPCell.NO_BORDER);
			    return cell;
			}
}
