package org.example.project1.schedule;

import java.security.Principal;

import org.example.project1.user.SiteUser;
import org.example.project1.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// 캘린더 작성
@Controller
@RequiredArgsConstructor
@RequestMapping("/calendar")
@PreAuthorize("isAuthenticated()")
public class ScheduleController {
	private final UserService userService;
	private final ScheduleService scheduleService;

	@GetMapping
	public String createSchedule(ScheduleForm scheduleForm) {
		return "calendar";
	}

	@PostMapping
	public String createSchedule(
		@Valid ScheduleForm scheduleForm,
		BindingResult bindingResult,
		Principal principal
	) {
		if (bindingResult.hasErrors()) {
			return "calendar";
		}

		if (scheduleForm.endDate().isBefore(scheduleForm.startDate())) {
			return "calendar";
		}

		SiteUser siteUser = userService.getUser(principal.getName());
		scheduleService.create(scheduleForm.title(), scheduleForm.startDate(), scheduleForm.endDate(), siteUser);
		return "calendar";
	}

	@DeleteMapping("/{id}")
	public String deleteSchedule(
		@PathVariable("id") Long id,
		Principal principal
	) {
		SiteUser siteUser = userService.getUser(principal.getName());
		scheduleService.delete(id, siteUser);
		return "calendar";
	}
}
