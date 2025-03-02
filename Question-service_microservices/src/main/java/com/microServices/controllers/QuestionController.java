package com.microServices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microServices.model.Question;
import com.microServices.model.QuestionWrapper;
import com.microServices.model.Response;
import com.microServices.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService service;
	
	@GetMapping("questions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return service.getAllQuestions();
	}
	
	@PostMapping("question")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question)
	{
		return service.saveQuestion(question);
	}
	@GetMapping("category/{cat}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String cat)
	{
		return service.getQuestionsByCategory(cat);
	}
	
	@GetMapping("generateQuestions")
	public ResponseEntity<List<Integer>> getQuizQuestions(@RequestParam String category,@RequestParam Integer numQ)
	{
		return service.getQuizQuestions(category,numQ);
	}
	@PostMapping("getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> returnQuizQuestions(@RequestBody List<Integer> questionIds)
	{
		return service.getQuesByIds(questionIds);
	}
	@PostMapping("calculateScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return service.calculateScore(responses);
	}
	
}
