package com.kayz.overflow.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kayz.overflow.models.Answer;
import com.kayz.overflow.models.Question;
import com.kayz.overflow.models.Tag;
import com.kayz.overflow.services.MainService;

@Controller
public class MainController {
	private final MainService service;
	
	public MainController(MainService service) {
		this.service = service;
	}
	
	@GetMapping("/questions")
	public String dashboard(Model model) {
		List<Question> questions = service.getAllQuestions();
		model.addAttribute("questions", questions);
		return "dashboard.jsp";
	}
	
	// CREATE QUESTION & TAGS
	@GetMapping("/questions/new")
	public String newQuestion(RedirectAttributes ra) {
		return "newQuestion.jsp";
	}
	@PostMapping("/questions")
	public String createQuestion(@RequestParam(value="question") String question, @RequestParam(value="tagsStr") String tagsStr, RedirectAttributes ra) {
		HashMap<String, String> errors = new HashMap<String, String>();
		if(question == "") {
			errors.put("question", "Question field is required");
		}
		String[] tags = tagsStr.split(", ");
		if(tags.length > 3) {
			errors.put("tag", "Only allow 3 tags");
		}
		if(!tagsStr.equals(tagsStr.toLowerCase())) {
			errors.put("tagerr", "Tags must be lowercase");
		}
		
		if(errors.isEmpty()) {
			Question newQuestion = service.createQuestion(new Question(question));
			for(String t: tags) {
				Tag existedTag = service.findTag(t);
				if(existedTag == null) {
					Tag tag = service.createTag(t);
					newQuestion.getTags().add(tag);
				} else {
					newQuestion.getTags().add(existedTag);
				}
			}
			service.createQuestion(newQuestion);
			return "redirect:/questions";
		} else {
			ra.addFlashAttribute("errors", errors);
			return "redirect:/questions/new";
		}
	}
	
	// SHOW QUESTION
	@GetMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model) {
		model.addAttribute("q",service.findQuestion(id));
		return "showQuestion.jsp";
	}
	// CREATE ANSWER
	@PostMapping("/questions/{id}")
	public String addAnswer(@PathVariable("id") Long id, @RequestParam(value="answer") String answer, RedirectAttributes ra, Model model) {
		Question question = service.findQuestion(id);
		if(answer == "") {
			model.addAttribute("q", question);
			ra.addFlashAttribute("error", "Answer field is required");
			return "redirect:/questions/" + id;
		} else {
			service.createAnswer(answer, id);
			return "redirect:/questions/" + id;
		}
	}
}
