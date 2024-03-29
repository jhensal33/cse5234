package edu.osu.cse5234.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Home {
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "Home";
	}		
	
	@RequestMapping(path = "/about", method = RequestMethod.GET)
	public String aboutUs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "AboutUs";
	}		
	
	@RequestMapping(path = "/contact", method = RequestMethod.GET)
	public String contactUs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "ContactUs";
	}		
	
	
}