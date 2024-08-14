package org.example.project1.home;

import org.example.project1.board.BoardService;
import org.example.project1.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

// 사용자들이 보는 메인페이지
@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;
	private final BoardService boardService;

//	@PreAuthorize("isAuthenticated()") // 인증된 유저라면? main 페이지의 get요청을 수행
	@GetMapping("/main")
	public String mainHome(Model model) {
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return "redirect:/"; //익명 유저(no token)이면 전시 페이지로 이동
		} //인증
//		Page<Board> hotList = boardService.getTop5HotList(); hot 게시물 필요X
//		model.addAttribute("questions", hotList);
		return "calendar"; // 여기는 ARTIST를 위한 페이지로 리디렉션
	}
}
