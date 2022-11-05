package com.example;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
@Autowired
	EmployeeRepo repo;

Logger log=Logger.getAnonymousLogger();

@RequestMapping("/")
public ModelAndView loadpage(HttpServletRequest request, HttpServletResponse response) {
	ModelAndView mv=new ModelAndView();
	log.info("entered into the default loding page");
	mv.setViewName("login.jsp");
	log.info("loaded the page");
	return mv;
}

@RequestMapping("/login")
//@RequestParam("user") String user
public ModelAndView checklogin(HttpServletRequest request, HttpServletResponse response) {
	log.info("entered into the login logic");
	ModelAndView mv=new ModelAndView();
	String user=request.getParameter("user");
	String password=request.getParameter("password");
	log.info("brought the values of user and password" + user + "  "+password);
	log.info("the query verification value " +repo.findByuser(user, password) );
	if(repo.findByuser(user, password)!=null) {
		log.info("verified the user and password");
		mv.setViewName("success.jsp");
		mv.addObject("user",user);
		log.info("control went to success.jsp");
	}
	else {
		log.info("control went to fail.jsp");
		mv.setViewName("fail.jsp");
	}
	return mv;
	}


RestTemplate rest=new RestTemplate();
@ResponseBody
@RequestMapping("/registermslogic")
//@RequestParam("user") String user
public String checkregister(HttpServletRequest request, HttpServletResponse response) {
	log.info("entered into the register microservice transfer of data logic");
	ModelAndView mv=new ModelAndView();
	String user=request.getParameter("user");
	String password=request.getParameter("password");
	String email=request.getParameter("email");
	String url="http://localhost:8060/register/"+user+"/"+password+"/"+email;
	log.info(url);
	rest.getForObject(url,String.class);
	
	//enhance the code to another jsp by model and view or redirect to login.jsp
	return "registered";
}	

}
