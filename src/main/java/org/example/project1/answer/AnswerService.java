package org.example.project1.answer;

import java.time.LocalDateTime;

import org.example.project1.DataNotFoundException;
import org.example.project1.board.Board;
import org.example.project1.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	private final AnswerRepository answerRepository;

	public Answer create(Board board, String content) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setBoard(board);
		return answerRepository.save(answer);
	}

	public Answer getAnswer(Long id) {
		return answerRepository.findById(id)
			.orElseThrow(() -> new DataNotFoundException("answer not found"));
	}

//	public void modify(Answer answer, String content) {
//		answer.setContent(content);
//		answer.setModifyDate(LocalDateTime.now());
//		this.answerRepository.save(answer);
//	}
//
//	public void delete(Answer answer) {
//		this.answerRepository.delete(answer);
//	}
//
//	public void vote(Answer answer, SiteUser siteUser) {
//		answer.getVoter().add(siteUser);
//		this.answerRepository.save(answer);
//	}
}
