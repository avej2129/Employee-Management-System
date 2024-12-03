package com.employees.application.utility;

import java.io.IOException;
import java.util.List;



import com.employees.application.model.Employee;
import com.itextpdf.text.TabStop.Alignment;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

public class PDF_EGenerator {

	// List to hold all Students
	private List<Employee> employeeList;

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph = new Paragraph("List Of Employees", fontTiltle);

				paragraph.setAlignment(Paragraph.ALIGN_CENTER);
				paragraph.setSpacingAfter(10);

				document.add(paragraph);

				PdfPTable table = new PdfPTable(5);

				table.setWidthPercentage(100f);
				table.setSpacingBefore(4);
				table.setHorizontalAlignment(table.ALIGN_CENTER);
				PdfPCell cell = new PdfPCell();

				cell.setBackgroundColor(CMYKColor.MAGENTA);
				cell.setPadding(8);
				cell.setHorizontalAlignment(cell.ALIGN_CENTER);

				Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				font.setColor(CMYKColor.WHITE);

				cell.setPhrase(new Phrase("Employee ID", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("First Name", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Last Name", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Email", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Phone Number", font));
				table.addCell(cell);

				cell.setHorizontalAlignment(cell.getHorizontalAlignment());

				for (Employee employee : employeeList) {
					table.addCell(String.valueOf(employee.getEmployeeId()));
					table.addCell(employee.getFirstName());
					table.addCell(employee.getLastName());
					table.addCell(employee.getEmail());
					table.addCell("+91 "+ employee.getPhoneNo());
				}

				document.add(table);
				document.close();
			}

}
		
