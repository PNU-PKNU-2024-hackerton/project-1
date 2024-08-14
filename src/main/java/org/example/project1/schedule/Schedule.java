package org.example.project1.schedule;

import java.time.LocalDateTime;

import org.example.project1.user.SiteUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

// 스케줄은 필요 X
@Getter
@Setter
@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String title;

	@ManyToOne
	private SiteUser user;
}
