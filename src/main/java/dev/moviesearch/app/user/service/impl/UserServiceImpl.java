package dev.moviesearch.app.user.service.impl;

import dev.moviesearch.app.user.domain.UserDto;
import dev.moviesearch.app.user.mapper.UserMapper;
import dev.moviesearch.app.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service("userService")
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
	@Override
	public List<UserDto> userList() {
		log.info("===============================================================");
	    log.info("user 로그 출력을 확인 합니다.");
	    log.info("===============================================================");
	    return userMapper.userList();
	}

	@Override
	public void insertUser(UserDto req) {
		userMapper.insertUser(req);
	}

	@Override
	public UserDto findUser(String userId) {
		return userMapper.findUser(userId);
	}

	@Override
	public void updateUser(String userId) {
		userMapper.updateUser(userId);
	}
	
	@Override
	public void deleteUser(String userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap();
		
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}




}
