package org.example.project1.schedule;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.example.project1.user.SiteUser;
import org.example.project1.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class ScheduleHandlerController {
	private final UserService userService;
	private final ScheduleService scheduleService;

	private List<Schedule> schedules = new ArrayList<>();

	@PostMapping
	public ResponseEntity<String> saveEvent(@RequestBody ScheduleForm event, Principal principal) {
		SiteUser user = userService.getUser(principal.getName());
		scheduleService.create(event.title(), event.startDate(), event.endDate(), user);

		return ResponseEntity.ok("ok");
	}

	@GetMapping
	public List<Schedule> getEvents(Principal principal) {
		SiteUser user = userService.getUser(principal.getName());
		return scheduleService.getScheduleList(user);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable Integer id) {
		schedules.removeIf(event -> event.getId().equals(id));
	}
}
