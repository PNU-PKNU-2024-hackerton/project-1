package org.example.project1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@GetMapping("/legend")
	@ResponseBody
	public String index() {
		return "저희는 천재입니다";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/user/login";
	}
}
