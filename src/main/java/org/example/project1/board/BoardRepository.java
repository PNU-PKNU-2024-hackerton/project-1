package org.example.project1.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//  키워드로 게시물 가져오기
//	@Query(value = "select "
//		+ "distinct q "
//		+ "from Board q "
//		+ "left outer join SiteUser u1 on q.author=u1 "
//		+ "left outer join Answer a on a.question=q "
//		+ "left outer join SiteUser u2 on a.author=u2 "
//		+ "where "
//		+ "   q.subject like %:kw% "
//		+ "   or q.content like %:kw% "
//		+ "   or u1.username like %:kw% "
//		+ "   or a.content like %:kw% "
//		+ "   or u2.username like %:kw% ")
//	Page<Board> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

//	@Query(value = "select new org.example.project1.board.ResponseBoard(b.id, b.name, b.author.username, b.description, b.createdAt, b.works, b.answers) " +
//		"from Board b")
//	List<ResponseBoard> findAllResBoards();



	// voter 좋아요 수 가져오기
	@Query("SELECT q FROM Board q WHERE SIZE(q.voter) > :voterCount")
	List<Board> findByVoterCountGreaterThan(int voterCount);
}
