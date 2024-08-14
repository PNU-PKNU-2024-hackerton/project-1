package org.example.project1.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.example.project1.DataNotFoundException;
import org.example.project1.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
	private final ScheduleRepository scheduleRepository;

	public void create(String title, LocalDateTime startDate, LocalDateTime endDate, SiteUser siteUser) {
		Schedule schedule = new Schedule();
		schedule.setUser(siteUser);
		schedule.setTitle(title);
		schedule.setStartDate(startDate);
		schedule.setEndDate(endDate);

		scheduleRepository.save(schedule);
	}

	public void delete(Long id, SiteUser siteUser) {
		Schedule schedule = scheduleRepository.findById(id)
			.orElseThrow(() -> new DataNotFoundException("스케줄을 찾을 수 없습니다."));

		if (!Objects.equals(schedule.getUser().getId(), siteUser.getId())) {
			throw new RuntimeException("사용자의 스케줄이 아닙니다.");
		}

		scheduleRepository.delete(schedule);
	}

	public List<Schedule> getScheduleList(SiteUser user) {
		return scheduleRepository.findAll()
			.stream()
			.filter(x -> x.getUser().equals(user))
			.filter(x -> x.getStartDate() != null)
			.toList();
	}
}
