package org.example.project1.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import org.example.project1.answer.Answer;
import org.example.project1.user.SiteUser;

import lombok.Getter;
import lombok.Setter;
import org.example.project1.works.Works;

// 작품 엔티티
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;

	@Column(length = 200)
	private String name;

	private String description;

	@Column(name = "created_at")
	private LocalDateTime createdAt; // 생성 날짜

	private Integer boardYear;

	@Column(name = "file_path")
	@NotEmpty
	private String filePath;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Answer> answers;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Works> works;

	@ManyToOne
	@JoinColumn(name = "siteuser_id")
	private SiteUser author;  // 작가 사용자

	private LocalDateTime modifyDate; // 수정된 날짜

	@ManyToMany
	Set<SiteUser> voter; // 좋아요
}