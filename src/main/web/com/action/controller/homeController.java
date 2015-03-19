package com.action.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
	    @RequestMapping("/home/index.do")
	    public String homeActionMethod(Model model) {
	        model.addAttribute("homeClass", "active");
	        return "home";
	    }
	    
	    @RequestMapping("/home/spv.do")
	    public String spvActionMethod(Model model) {
	        model.addAttribute("spvClass", "active");
	        return "spv";
	    }
	    
	    @RequestMapping("/home/sprt.do")
	    public String sprtActionMethod(Model model) {
	        model.addAttribute("sprtClass", "active");
	        return "sprt";
	    }
	    
	    @RequestMapping("/home/reports.do")
	    public String reportsActionMethod(Model model) {
	        model.addAttribute("reportClass", "active");
	        return "reports";
	    }
}
