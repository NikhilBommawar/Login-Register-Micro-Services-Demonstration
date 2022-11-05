package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo  extends JpaRepository<Employee,String>{

@Query("select employee from Employee employee where employee.user=?1 and employee.password=?2")	
	public Employee findByuser(String name,String password);
	
}

