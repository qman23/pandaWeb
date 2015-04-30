package com.action.bluePage;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.action.controller.ActionController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ibm.glp.bluepages.BPFinder;
import com.utils.business.Utils;

@Controller
public class bluePagecontroller extends ActionController{

	
	
	@RequestMapping(value = "/home/bluePage.do", method = RequestMethod.GET)
	public ModelAndView initBluePage(){
		ModelAndView mv = new ModelAndView("bluePage/queryBluePage");
		mv.addObject("bluePageClass", "active");
		mv.addObject("BPcurrentTab", "in");
		return mv;
	}
	
	@RequestMapping(value = "/home/queryBP.do", method = RequestMethod.GET)
	public void getBPinformation(String queryType,String queryValue, PrintWriter writer){
		try {
			if(!Utils.isStringNull(queryValue)){
				BPFinder finder = new BPFinder();
				writer.write(JSON.toJSONString(finder.searchBP(queryValue,queryType),
						SerializerFeature.PrettyFormat));
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			writer.write(JSON.toJSONString(e.getMessage(),
					SerializerFeature.PrettyFormat));
		}finally{
			writer.close();
		}
		
	}
	
	
}
