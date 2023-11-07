package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

		private EmployeeRepository employeeRepository;
			
		

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
			super();
			this.employeeRepository = employeeRepository;
		}
 

 
	@Override
	public Employee savEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}


	//Returns all Employees
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}
	
	
	//Returns EmployeesByID
	@Override 
	public Employee getEmployeeById(long id) {
		
//		Optional<Employee> employee = employeeRepository.findById(id);
//		
//		if (employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		
		return employeeRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Employee","ID", id));
}


	
	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}



	@Override
	public void deleteEmployee(long id) {
		
		employeeRepository.findById(id).orElseThrow( () ->  new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
		
	}

 

}
