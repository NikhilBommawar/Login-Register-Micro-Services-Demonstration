package com.example;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
@Autowired
	EmployeeRepo repo;

Logger log=Logger.getAnonymousLogger();
@ResponseBody
@RequestMapping("/register/{user}/{password}/{email}")
public String registermicroservicelogic(HttpServletRequest request, HttpServletResponse response,@PathVariable("user") String user,@PathVariable("password") String password,@PathVariable("email") String email) {
	
	log.info("inside the register ms logic ");
	Employee e=new Employee();
	e.setUser(user);
	e.setPassword(password);
	e.setEmail(email);
	log.info("data set");
	if(repo.save(e)!=null) {
	log.info("inserted");
	return "done";
	}
	else {
		return "fail";
	}
}



	
}
