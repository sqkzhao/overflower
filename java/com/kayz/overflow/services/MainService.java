package com.kayz.overflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kayz.overflow.models.Answer;
import com.kayz.overflow.models.Question;
import com.kayz.overflow.models.Tag;
import com.kayz.overflow.repositories.AnswerRepo;
import com.kayz.overflow.repositories.QuestionRepo;
import com.kayz.overflow.repositories.TagRepo;

@Service
public class MainService {
	private final QuestionRepo quesRepo;
	private final AnswerRepo anRepo;
	private final TagRepo tagRepo;
	
	public MainService(QuestionRepo quesRepo, AnswerRepo anRepo, TagRepo tagRepo) {
		this.quesRepo = quesRepo;
		this.anRepo = anRepo;
		this.tagRepo = tagRepo;
	}
	
	// GET ALL
	public List<Question> getAllQuestions(){
		return quesRepo.findAll();
	}
	
	// FIND ONE
	public Question findQuestion(Long id) {
		return quesRepo.findById(id).orElse(null);
	}
	
	public Tag findTag(String subject) {
		return tagRepo.findBySubject(subject);
	}
	
	// CREATE
	public Question createQuestion(Question question) {
		return quesRepo.save(question);
	}
	public Tag createTag(String subject) {
		Tag newTag = new Tag(subject);
		return tagRepo.save(newTag);
	}
	public Answer createAnswer(String answer, Long id) {
		Question q = quesRepo.findById(id).orElse(null);
		Answer newAnswer = new Answer(answer, q);
		return anRepo.save(newAnswer);
	}
}
