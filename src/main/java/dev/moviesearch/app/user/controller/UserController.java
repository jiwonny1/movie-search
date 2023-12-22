package dev.moviesearch.app.user.controller;

import dev.moviesearch.app.user.domain.UserDto;
import dev.moviesearch.app.user.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/go_join")
	public String goJoin() {
		return "user/goJoin";
	}

	@GetMapping("join")
	public String join(@Valid UserDto dto, BindingResult result) {
		userService.insertUser(dto);
		
		if(result.hasErrors()) {
			return "user/goJoin";
		}
		
		return "user/join";
	}
	
}
