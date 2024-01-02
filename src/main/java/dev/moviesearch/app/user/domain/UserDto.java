package dev.moviesearch.app.user.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	
	@NotBlank(message="이름은 필수 입력 값입니다.")
	private String name;
	
	//생년월일을 입력하면 자동으로 나이를 추가하게..
	@NotNull(message="나이는 1 이상의 값이어야 합니다.")
	@Min(value = 1)
	private Integer age;
	
	@NotBlank(message="아이디는 필수 입력 값입니다.")
	@Max(value=12, message = "아이디는 최대 12글자 이내로 입력해주세요.")
    private String userId;
	
	@NotBlank(message="비밀번호는 필수 입력 값입니다.")
	@Size(min=6, max=12, message = "비밀번호는 최소 6글자, 최대 12글자로 입력해주세요.")
	private String password;
    
    @NotBlank(message="하나 이상의 장르를 선택해주세요.")
    private String genre;
    
	@Builder
	public UserDto(String name, Integer age, String userId, String password, String genre) {
		this.name = name;
		this.age = age;
		this.userId = userId;
		this.password = password;
		this.genre = genre;
	}
	
    
}
