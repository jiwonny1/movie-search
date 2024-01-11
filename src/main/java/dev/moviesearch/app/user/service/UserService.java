package dev.moviesearch.app.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import dev.moviesearch.app.user.domain.UserDto;

public interface UserService {
	
	List<UserDto> userList();
	
	void insertUser(UserDto reqs);
	
	UserDto findUser(String userId);
	
	void updateUser(String userId);
	
	void deleteUser(String userId);

	Map<String, String> validateHandling(Errors errors);
}
