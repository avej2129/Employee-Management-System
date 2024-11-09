package com.employees.application.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.employees.application.model.DeletedEmployee;
import com.employees.application.repository.DeletedEmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employees.application.model.Employee;
import com.employees.application.repository.EmployeeRepository;
import com.employees.application.service.EmployeeService;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DeletedEmployeeRepo deletedEmployeeRepo;

	@Override
	public List<Employee> findAllEmployees() {
		log.info("Finding the employee list");
		return employeeRepository.findAll();
	}

	@Override
	public Employee findEmployeeById(int id) {
		Optional<Employee> result = employeeRepository.findById(id);
		return result.get();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		log.info("Save the employee details");
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> result = employeeRepository.findById(employee.getId());
		log.info("Updating the employee details");
		Employee existing = result.get();
		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		existing.setEmail(employee.getEmail());
		existing.setPhoneNo(employee.getPhoneNo());
		return employeeRepository.save(existing);

	}

	@Override
	public void deleteEmployeeById(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()){
			DeletedEmployee deletedEmployee = modelMapper.map(employee.get(),DeletedEmployee.class);
			log.info("Save employee details before deleting");
			deletedEmployeeRepo.save(deletedEmployee);
			log.info("Employee deleted successfully");
			employeeRepository.deleteById(id);
		}
	}

	@Override
	public List<String> getAllPhoneNumber() {
		return employeeRepository.getPhoneNumber();
	}

	@Override
	public List<DeletedEmployee> findDeletedEmployee() {
		log.info("Finding all deleted employee.");
		return deletedEmployeeRepo.findAll();
	}
}


