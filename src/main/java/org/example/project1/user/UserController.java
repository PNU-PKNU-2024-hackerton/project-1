package org.example.project1.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserCreateForm userCreateForm, HttpServletResponse response) {
		Cookie cookie = new Cookie("TOKEN", "ARTIST");
		cookie.setHttpOnly(true); // 클라이언트 측 스크립트에서 접근 불가
		cookie.setPath("/"); // 모든 경로에서 접근 가능
		cookie.setMaxAge(999999999); // 쿠키 유효 기간 (1시간)
		response.addCookie(cookie);
		SiteUser user = SiteUser.builder()
				.username(userCreateForm.getUsername())
				.password(userCreateForm.getPassword())
				.email(userCreateForm.getEmail())
				.token("")
				.build();
		userService.save(user);
		return ResponseEntity.status(200).body("회원가입완료");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) {
		SiteUser user = userService.getUser(requestLogin.getEmail());
		if (user != null) {
			if(user.getPassword().equals(requestLogin.getPassword())) {
				user.setToken("ARTIST");

				return ResponseEntity.status(200).body("로그인 완료");
			} else {
				throw new RuntimeException("패스워드 불일치");
			}
		} else {
			return ResponseEntity.status(400).body("유저 없음");
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody String email, HttpServletResponse response) {
		userService.getUser(email).setToken("");
		Cookie cookie = new Cookie("TOKEN", "ARTIST");
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseEntity.status(200).body("로그아웃 완료");
	}


//	@PostMapping("/signup")
//	public ResponseEntity<String> signup(@Valid @RequestBody UserCreateForm userCreateForm, BindingResult bindingResult) {
//		// Validate input
//		if (bindingResult.hasErrors()) {
//			String errorMessage = bindingResult.getAllErrors().stream()
//					.map(error -> error.getDefaultMessage())
//					.reduce((msg1, msg2) -> msg1 + ", " + msg2)
//					.orElse("회원 가입에 실패했습니다.");
//			return ResponseEntity.badRequest().body(errorMessage);
//		}
//
//		// Check if passwords match
//		if (!userCreateForm.getPassword().equals(userCreateForm.getCpassword())) {
//			return ResponseEntity.badRequest().body("패스워드와 확인 비밀번호가 일치하지 않습니다.");
//		}
//
//		try {
//			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
//		} catch (DataIntegrityViolationException e) {
//			return ResponseEntity.badRequest().body("이미 등록된 사용자입니다.");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//		}
//
//		return ResponseEntity.ok("회원 가입 성공");
//	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//		try {
//			SiteUser loginUser = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
//			return ResponseEntity.ok(loginUser);
//		} catch (DataNotFoundException | IllegalArgumentException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//		}
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/change/password")
//	public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordChangeForm passwordChangeForm, BindingResult bindingResult, Principal principal) {
//		if (bindingResult.hasErrors()) {
//			String errorMessage = bindingResult.getAllErrors().stream()
//					.map(error -> error.getDefaultMessage())
//					.reduce((msg1, msg2) -> msg1 + ", " + msg2)
//					.orElse("비밀번호 변경에 실패했습니다.");
//			return ResponseEntity.badRequest().body(errorMessage);
//		}
//
//		if (!passwordChangeForm.getPassword1().equals(passwordChangeForm.getPassword2())) {
//			return ResponseEntity.badRequest().body("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//		}
//
//		SiteUser user = userService.getUser(principal.getName());
//		userService.update(user, passwordChangeForm.getPassword1());
//
//		return ResponseEntity.ok("비밀번호 변경 성공");
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/mypage")
//	public ResponseEntity<SiteUser> mypage(Principal principal) {
//		SiteUser user = userService.getUser(principal.getName());
//		return ResponseEntity.ok(user);
//	}
//
//	// 데이터 전송을 위한 DTO 클래스
//	public static class LoginRequest {
//		private String email;
//		private String password;
//
//		// getters and setters
//		public String getEmail() {
//			return email;
//		}
//
//		public void setEmail(String email) {
//			this.email = email;
//		}
//
//		public String getPassword() {
//			return password;
//		}
//
//		public void setPassword(String password) {
//			this.password = password;
//		}
//	}
}
