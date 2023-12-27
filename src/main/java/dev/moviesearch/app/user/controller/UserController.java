package dev.moviesearch.app.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dev.moviesearch.app.user.domain.UserDto;
import dev.moviesearch.app.user.service.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/go_join")
	public String goJoin() {
		return "user/goJoin";
	}

	@PostMapping("/join")
	public String join(@Valid UserDto dto, Errors errors, Model model) {
		if(errors.hasErrors()) {
			return "user/goJoin";
		}
		
		model.addAttribute("userDto", dto);
		userService.insertUser(dto);
		
		return "user/join";
	}
	
}



