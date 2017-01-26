package com.adaming.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adaming.myapp.service.IServiceAgentImmobilier;
import com.adaming.myapp.service.IServiceBien;
import com.adaming.myapp.service.IServiceClient;
import com.adaming.myapp.service.IServiceVisite;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IServiceAgentImmobilier serviceAgentImmobilier;
	@Autowired
	private IServiceBien serviceBien;
	@Autowired
	private IServiceClient serviceClient;
	@Autowired
	private IServiceVisite serviceVisite;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("serviceAgentImmobilier", serviceAgentImmobilier);
		model.addAttribute("serviceBien", serviceBien);
		model.addAttribute("serviceClient", serviceClient);
		model.addAttribute("serviceVisite", serviceVisite);
		
		model.addAttribute("test", "test");
		
		return "home";
	}
	
}
