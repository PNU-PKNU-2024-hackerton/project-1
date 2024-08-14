package org.example.project1.answer;

import org.example.project1.board.Board;
import org.example.project1.board.BoardService;
import org.example.project1.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

// 코멘트 작성
@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

	private final BoardService boardService;
	private final AnswerService answerService;

	// 방명록 작성
//	@PreAuthorize("isAuthenticated()")
	@PostMapping("/{exhibitionid}")
	public ResponseEntity<?> createAnswer(@PathVariable("exhibitionid") Long id, @RequestBody RequestAnswer requestAnswer) {
		// 방명록을 작성할 전시물을 가져옴
		Board board = boardService.getBoard(id);

//		// 현재 사용자를 가져옴
//		SiteUser siteUser = this.userService.getUser(principal.getName());

		// 유효성 검사에서 오류가 발생한 경우
//		if (bindingResult.hasErrors()) {
//			// 에러 메시지를 반환함
//			String errorMessage = bindingResult.getAllErrors().stream()
//					.map(error -> error.getDefaultMessage())
//					.reduce((msg1, msg2) -> msg1 + ", " + msg2)
//					.orElse("잘못된 입력입니다.");
//			return ResponseEntity.badRequest().body(errorMessage);
//		}

		// 방명록(답변)을 생성함
		answerService.create(board, requestAnswer.getContent());

		// 성공적으로 생성되었다는 메시지와 생성된 답변 객체를 JSON 형태로 반환함
		return ResponseEntity.status(200).body("방명록 작성이 완료되었습니다.");
	}
//	public String createAnswer(Model model, @PathVariable("exhibitionid") Long id,
//		@Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
//		Board board = this.boardService.getBoard(id);
//		SiteUser siteUser = this.userService.getUser(principal.getName());
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("question", board);
//			return "question_detail";
//		}
//		Answer answer = this.answerService.create(board,
//			answerForm.getContent(), siteUser);
//		return String.format("redirect:/question/detail/%s#answer_%s",
//			answer.getBoard().getId(), answer.getId());
//	}

//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/modify/{id}")
//	public String answerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal) {
//		Answer answer = this.answerService.getAnswer(id);
//		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
//		answerForm.setContent(answer.getContent());
//		return "answer_form";
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/modify/{id}")
//	public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
//		@PathVariable("id") Long id, Principal principal) {
//		if (bindingResult.hasErrors()) {
//			return "answer_form";
//		}
//		Answer answer = this.answerService.getAnswer(id);
//		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//		}
//		this.answerService.modify(answer, answerForm.getContent());
//		return String.format("redirect:/question/detail/%s#answer_%s",
//			answer.getBoard().getId(), answer.getId());
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/delete/{id}")
//	public String answerDelete(Principal principal, @PathVariable("id") Long id) {
//		Answer answer = this.answerService.getAnswer(id);
//		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//		}
//		this.answerService.delete(answer);
//		return String.format("redirect:/question/detail/%s", answer.getBoard().getId());
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/vote/{id}")
//	public String answerVote(Principal principal, @PathVariable("id") Long id) {
//		Answer answer = this.answerService.getAnswer(id);
//		SiteUser siteUser = this.userService.getUser(principal.getName());
//		this.answerService.vote(answer, siteUser);
//		return String.format("redirect:/question/detail/%s#answer_%s",
//			answer.getBoard().getId(), answer.getId());
//	}
}