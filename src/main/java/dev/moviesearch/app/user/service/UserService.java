package dev.moviesearch.app.user.service;

import java.util.List;
import dev.moviesearch.app.user.domain.UserDto;

public interface UserService {
	
	List<UserDto> userList();
	
	void insertUser(UserDto req);
	
	UserDto findUser(String userId);
	
	void updateUser(String userId);
	
	void deleteUser(String userId);
}
