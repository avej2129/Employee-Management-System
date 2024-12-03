package com.employees.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.employees.application.model.Employee;
import com.employees.application.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/home")
	public String findAll(Model model) {
		model.addAttribute("ListEmployees", employeeService.findAllEmployees());
		return "home-page";
	}
	 
	@GetMapping("/add")
	@PreAuthorize("hasAuthorize('ADMIN')")
	public String addEmployee(Model model) {
		model.addAttribute("Employees", new Employee());
		return "add-employee";
	}

	@PostMapping("/addEmployee")
	public String createEmployee(Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/employee/home";
	}
	 
	@GetMapping("/update/{id}")
	@PreAuthorize("hasAuthorize('ADMIN')")
	public String employeeUpdatePage(Model model, @PathVariable("id") int id) {
		model.addAttribute("employee", employeeService.findEmployeeById(id));
		return "update-employee";
	}

	@PostMapping("/updateEmployee")
	@PreAuthorize("hasAuthorize('ADMIN')")
	public String updateEmployee(Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/employee/home";
	}
	 
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthorize('ADMIN')")
	public String deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employee/deleted-employee";
	}

	@GetMapping("/deleted-employee")
	public String findAllDeletedEmployee(Model model) {
		model.addAttribute("ListDeletedEmployees", employeeService.findDeletedEmployee());
		return "deleted-employee";
	}

	@GetMapping("/restoreEmployee/{id}")
	public String restoreEmployee(@PathVariable int id){
		employeeService.restoreEmployee(id);
		return "redirect:/employee/home";
	}
	@GetMapping("/permanentEmployeeDeleted/{id}")
	public String permanentDeleteEmployee(@PathVariable int id){
		employeeService.permanentDeleteEmployee(id);
		return "redirect:/employee/deleted-employee";
	}
}
