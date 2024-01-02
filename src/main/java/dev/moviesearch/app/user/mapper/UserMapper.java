package dev.moviesearch.app.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.validation.Errors;

import dev.moviesearch.app.user.domain.UserDto;

@Mapper
public interface UserMapper {
	
	List<UserDto> userList();
	
	public void insertUser(UserDto req);
	
	UserDto findUser(String userId);
	
	void updateUser(String userId);
	
	void deleteUser(String userId);
	
	Map<String, String> validateHandling(Errors errors);
}

