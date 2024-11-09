package com.employees.application.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.application.model.Employee;
import com.employees.application.service.EmployeeService;

import com.employees.application.utility.PDF_EGenerator;
import com.lowagie.text.DocumentException;

@RestController

public class PDFController {
	
	@Autowired
	private EmployeeService service;

	@GetMapping("/EmployeePdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {

		response.setContentType("/application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		List<Employee> employeeList = service.findAllEmployees();

		PDF_EGenerator generator = new PDF_EGenerator();
		generator.setEmployeeList(employeeList);
		generator.generate(response);

	}
	 

}
