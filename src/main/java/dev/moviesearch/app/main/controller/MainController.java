package dev.moviesearch.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	

	@GetMapping("/")
	public String getIndex(HttpSession session, Model model) {
		return "index";
	}
	
}
