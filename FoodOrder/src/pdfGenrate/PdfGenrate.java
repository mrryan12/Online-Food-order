package pdfGenrate;

import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import company.Company;
import orders.Order;
import orders.OrderItem;

public class PdfGenrate {
	

	public void pdfg(Order order,Company company) throws FileNotFoundException
	{
	
	  PdfWriter pdfw = new PdfWriter("D:\\Bootcamp/orderFood.pdf");
	  PdfDocument pdf = new PdfDocument(pdfw);
	  pdf.addNewPage();
	  Document document = new Document(pdf);
	  
	  List<OrderItem> item  = order.getOrderItem();
	  
	  Paragraph p1 = new Paragraph("Item id\t\tItem Name\t\tItem Price\t\tItem Quantity");
	  document.add(p1);
	  
	  for(OrderItem items : item)
	  { 
		  Paragraph p2 = new Paragraph(+items.getItems().getItemId()+"\t\t" +items.getItems().getItemName() +"\t\t"+items.getItems().getItemPrice() +"\t\t"+items.getQuantity());			 
		 document.add(p2);
	  }
	  
	  Paragraph p3 =  new Paragraph("Total Amount - "+company.getTotalWorthOfOrdersPlaced());
	  document.add(p3);
  
	  document.close();

     }
}
