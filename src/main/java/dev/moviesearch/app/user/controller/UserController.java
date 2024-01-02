package dev.moviesearch.app.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
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
	public String goJoin(UserDto dto) {
		return "user/goJoin";
	}

	@PostMapping("/join")
	public String join(@Valid UserDto dto, Errors errors, Model model) {
		 if (errors.hasErrors()) {
	            // 회원가입 실패시, 입력 데이터를 유지
	            model.addAttribute("userDto", dto);

	            // 유효성 통과 못한 필드와 메시지를 핸들링
	            Map<String, String> validatorResult = userService.validateHandling(errors);
	            for (String key : validatorResult.keySet()) {
	                model.addAttribute(key, validatorResult.get(key));
	            }

	            return "user/goJoin";
	        }

	        userService.insertUser(dto);
	        return "join";

	}
	
	@GetMapping("join")
	public String joinPage() {
		return "user/join";
	}
	
	
}