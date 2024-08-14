package org.example.project1.schedule;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record ScheduleForm(
	@NotNull
	@Length(max = 10)
	String title,
	@NotNull
	LocalDateTime startDate,
	@NotNull
	LocalDateTime endDate
) {
}
