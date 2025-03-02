package com.microServices.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microServices.model.QuestionWrapper;
import com.microServices.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
	@GetMapping("question/generateQuestions")
	public ResponseEntity<List<Integer>> getQuizQuestions(@RequestParam String category,@RequestParam Integer numQ);
	
	@PostMapping("question/getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> returnQuizQuestions(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/calculateScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	

}
