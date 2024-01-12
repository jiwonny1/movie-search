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
	
	@GetMapping("/go-join") //회원가입 페이지로 이동
	public String goJoin(UserDto dto) {
		return "user/goJoin";
	}

	@PostMapping("/join") //회원가입 기능
	public String join(@Valid UserDto dto, Errors errors, Model model) {
		 if (errors.hasErrors()) {
	            // 회원가입 실패시, 입력 데이터를 유지
	            model.addAttribute("userDto", dto);

	            // 유효성 통과 못한 필드와 메시지를 핸들링
//	            Map<String, String> validatorResult = userService.validateHandling(errors);
//	            for (String key : validatorResult.keySet()) {
//	                model.addAttribute(key, validatorResult.get(key));
//	            }

	            return "user/goJoin";
	        }

	        userService.insertUser(dto);
	        return "user/join";

	}
	
	@GetMapping("/go-login") //로그인 페이지로 이동
	public String goLoin(UserDto dto) {
		return "user/goLogin";
	}
	
//	@PostMapping("/login") //로그인 기능
//	public String login(String userId, HttpSession session) {
//		
//		
//		// 조금 더 생각을 하자...
//	}
	
	
	
}