package org.example.project1.user;

import org.example.project1.DataNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;

//	public SiteUser create(String username, String email, String password) {
//		SiteUser user = new SiteUser();
//		user.setUsername(username);
//		user.setEmail(email);
//		user.setPassword(passwordEncoder.encode(password));
//		userRepository.save(user);
//		return user;
//	}

	public void save(SiteUser user) {
		userRepository.save(user);
	}


	public SiteUser getUser(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new DataNotFoundException("siteuser not found"));
	}

//	public void update(SiteUser user, String newPassword) {
//		user.setPassword(passwordEncoder.encode(newPassword));
//		userRepository.save(user);
//	}

	// 로그인 메서드 추가
//	public SiteUser login(String email, String password) {
//		SiteUser user = userRepository.findByEmail(email)
//				.orElseThrow(() -> new DataNotFoundException("User not found"));
//
//		if (passwordEncoder.matches(password, user.getPassword())) {
//			return user; // 로그인 성공
//		} else {
//			throw new IllegalArgumentException("Invalid password"); // 로그인 실패
//		}
//	}
}
