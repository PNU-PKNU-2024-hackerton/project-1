package org.example.project1.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeForm {
	@NotBlank(message = "새 비밀번호는 필수입니다.")
	private String password1;

	@NotBlank(message = "비밀번호 확인은 필수입니다.")
	private String password2;
}
