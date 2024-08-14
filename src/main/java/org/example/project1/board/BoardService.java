package org.example.project1.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.example.project1.DataNotFoundException;
import org.example.project1.user.SiteUser;
import org.example.project1.works.WorksService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final WorksService worksService;

	/*
	public Board getList(Integer id) {
//		List<Sort.Order> sorts = new ArrayList<>();
//		sorts.add(Sort.Order.desc("createDate")); // 만든 날짜 기준으로 굳이?
//		Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));
//		return this.boardRepository.findAllByKeyword(kw, pageable);
//		return boardRepository.findAll();
	}
*/

	public Board getBoard(Long id) {
		Optional<Board> question = boardRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}

	public List<Board> getAllBoards(){
		List<Board> boards = boardRepository.findAll();
		if(!boards.isEmpty()){
			return boards;
		} else {
			throw new DataNotFoundException("cannot find boards");
		}
	}

	public void create(String name, int year, SiteUser user, List<MultipartFile> images) {
		Board q = new Board();
		q.setName(name);
		q.setBoardYear(year);
		q.setAuthor(user);
		boardRepository.save(q);
		worksService.uploadWorks(q, images); // 이미지 저장
	}

	// 수정
	public void modify(Board board, String fileName, String filePath) {
//		board.setArtistName(subject);
		board.setFilePath(filePath);
		board.setModifyDate(LocalDateTime.now());
		boardRepository.save(board);
	}

	public void delete(Board board) {
		boardRepository.delete(board);
	}

	// 좋아요 기능
	public void vote(Board board, SiteUser siteUser) {
		board.getVoter().add(siteUser);
		boardRepository.save(board);
	}

	// 좋아요 수 조회 (Get the vote count)
	public int getVoteCount(Board board) {
		return board.getVoter().size();
	}

	// 좋아요 (voter) 가 많은 5개
/*
	public Page<Board> getTop5HotList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
		Pageable pageable = PageRequest.of(0, 5, sort);
		Page<Board> questions = boardRepository.findByVoterCountGreaterThan(0, pageable);
		return questions;
		// return questions.stream()
		// 	.map(question ->
		// 		new HotQuestionDto(
		// 			question.getId(),
		// 			question.getSubject(),
		// 			(long)question.getVoter().size(),
		// 			(long)question.getAnswerList().size()
		// 		)
		// 	).toList();
	}

	public record HotQuestionDto(
		Integer questionId,
		String subject,
		Long recommendCount,
		Long commentCount
	) {
	}
*/
}