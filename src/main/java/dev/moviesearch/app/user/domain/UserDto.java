package dev.moviesearch.app.user.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	
	@NotBlank(message="아이디는 필수 입력 값입니다.")
	@Size(min=6, max=12)
    private String userId;
	
	//생년월일을 입력하면 자동으로 나이를 추가하게..
    private int age;
    
    @Size(min=6, max=12, message = "비밀번호는 최소 6글자, 최대 12글자로 입력해주세요.")
    private String password;
    
    @NotBlank(message="하나 이상의 장르를 선택해주세요.")
    private String genre;
    
    @NotBlank(message="이름은 필수 입력 값입니다.")
    private String name;
    

    
//    
//	@Override
//	public String toString() {
//		return "UserDto [userId=" + userId + ", age=" + age + ", password=" + password + ", genre=" + genre + ", name="
//				+ name + "]";
//	}
//    
    
}
