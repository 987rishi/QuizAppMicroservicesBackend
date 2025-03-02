package com.microServices.controller;

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

import com.microServices.model.QuestionWrapper;
import com.microServices.model.Quiz;
import com.microServices.model.Response;
import com.microServices.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService service;
	
	@PostMapping("createQuiz")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title)
	{
		return service.createQuiz(category,numQ,title);
	}
	@GetMapping("quizs")
	public ResponseEntity<List<Quiz>> getAllQuiz()
	{
		return service.getAllQuiz();
	}
	
	@GetMapping("quiz/{quizId}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId)
	{
		return service.getQuizQuestions(quizId);
	}
	
	@PostMapping("result/{id}")
	public ResponseEntity<Integer> calculateScore(@PathVariable Integer id,@RequestBody List<Response> responses)
	{
		return service.calculateScore(id,responses);
	}
	
	
}
