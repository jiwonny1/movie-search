package dev.moviesearch.app.user.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String userId;
    private int age;
    private String password;
    private String genre;
    private String name;
    
//    
//	@Override
//	public String toString() {
//		return "UserDto [userId=" + userId + ", age=" + age + ", password=" + password + ", genre=" + genre + ", name="
//				+ name + "]";
//	}
//    
    
}
