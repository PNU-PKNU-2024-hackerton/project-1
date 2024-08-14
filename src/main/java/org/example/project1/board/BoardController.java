package org.example.project1.board;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.project1.DataNotFoundException;
import org.example.project1.user.SiteUser;
import org.example.project1.user.UserRepository;
import org.example.project1.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.View;

// 전시물 작성
@RestController
@RequiredArgsConstructor
@RequestMapping("/exhibition")
public class BoardController {
	private final BoardService boardService;
	private final UserService userService;
	private final UserRepository userRepository;
//	private final View error;


	// 전시 목록 조회
	@GetMapping("/list")
	public ResponseEntity<List<Board>> getAllBoards() {
//		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
//			return "redirect:/";
//		}

		List<Board> boards = boardService.getAllBoards();
		if(boards.isEmpty()){
			throw new DataNotFoundException("Cannot find boards");
		}
		return ResponseEntity.status(200).body(boards);
	}


//	// 전시 디테일 조회
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/details/{id}")
	public ResponseEntity<ResponseBoard> detail(@PathVariable("id") Long id) {
		Board board = boardService.getBoard(id);

		return ResponseEntity.status(200).body(
				ResponseBoard.builder()
						.id(board.getId())
						.name(board.getName())
						.artist(board.getAuthor().getUsername())
						.createdAt(board.getCreatedAt())
						.works(board.getWorks())
						.answers(board.getAnswers())
						.build()
		);
	}

	// 전시 조회
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{id}")
	public ResponseEntity<ResponseBoard> boardCreate(@PathVariable("id") Long id) {
		Board board = boardService.getBoard(id);
		if(board != null){
			return ResponseEntity.status(200).body(
					ResponseBoard.builder()
							.id(board.getId())
							.name(board.getName())
							.artist(board.getAuthor().getUsername())
							.works(board.getWorks())
							.description(board.getDescription())
							.build()
			);
		} else {
			throw new DataNotFoundException("cannot find board" + id);
		}
    }

	// 전시 생성
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public ResponseEntity<String> createBoard(
			@RequestParam("name") String name,
			@RequestParam("year") int year,
			@Valid @RequestParam("images") List<MultipartFile> images,
			HttpServletRequest request) {
//		if(principal == null){
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no Principal found");
//		}
//		System.out.println(principal);
//		SiteUser siteUser = userService.getUser(principal.getName());
//		boardService.create(name, year, siteUser, images);
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				SiteUser user = userRepository.findByToken(cookie.getValue());
				if(user != null){
					boardService.create(name, year, user, images);
					return ResponseEntity.status(201).body("Board created successfully");
				} else {
					return ResponseEntity.status(403).body("User does not exist");
				}
			}
		} else {
			return ResponseEntity.status(403).body("Cookie doesn't exist");
		}
        return ResponseEntity.status(500).body("알 수 없는 에러");
    }

//	// 게시물 수정
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/modify/{id}")
//	public String questionModify(BoardForm boardForm, @PathVariable("id") Long id, Principal principal) {
//		Board board = this.boardService.getBoard(id);
//		if (!board.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
//		boardForm.setArtistName(board.getAuthor().getUsername());
//		boardForm.setFilePath(board.getFilePath());
//		return "question_form";
//	}

//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/modify/{id}")
//	public String questionModify(@Valid BoardForm boardForm, BindingResult bindingResult,
//								 Principal principal, @PathVariable("id") Long id) {
//		if (bindingResult.hasErrors()) {
//			return "question_form";
//		}
//		Board board = this.boardService.getBoard(id);
//		if (!board.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
//		this.boardService.modify(board, boardForm.getArtistName(), boardForm.getFilePath());
//		return String.format("redirect:/question/detail/%s", id);
//	}

	// 작품 삭제
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> boardDelete(Principal principal, @PathVariable("id") Long id) {
		Board board = boardService.getBoard(id);
		if (!board.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.boardService.delete(board);
		return ResponseEntity.status(200).body("Delete the board successful");
	}

	// 추천하기
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/vote/{id}")
	public ResponseEntity<String> voteExhibition(@PathVariable("exhibitionId") Long exhibitionId, Principal principal) {
		Board board = this.boardService.getBoard(exhibitionId);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.vote(board, siteUser);
		return ResponseEntity.status(200).body("추천이 완료되었습니다.");
	}

	// 추천 수 조회
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public ResponseEntity<String> questionVote(Principal principal, @PathVariable("id") Long id) {
		Board board = this.boardService.getBoard(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.boardService.vote(board, siteUser);
		return ResponseEntity.status(200).body("");
	}

	// 내(작가) 페이지
//	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage")
	public ResponseEntity<?> mypage(Principal principal, Model model){
		SiteUser user = userService.getUser(principal.getName());
		model.addAttribute("current_user", user);
		List<Board> boards = boardService.getAllBoards();
		return ResponseEntity.status(200).body(boards);
	}
}