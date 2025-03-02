package com.microServices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microServices.feign.QuizInterface;
import com.microServices.model.QuestionWrapper;
import com.microServices.model.Quiz;
import com.microServices.model.Response;
import com.microServices.repo.QuizRepo;

@Service
public class QuizService {

	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuizInterface quizInterface;
	

	public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {
		
		List<Integer> listOfQuizQuestions=quizInterface.getQuizQuestions(category,numQ).getBody();
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setSetOfQuestionId(listOfQuizQuestions);
		
		
		return new ResponseEntity<>(quizRepo.save(quiz),HttpStatus.CREATED);
		
	}


	public ResponseEntity<List<Quiz>> getAllQuiz() {
		return new ResponseEntity<>(quizRepo.findAll(),HttpStatus.OK);
				
	}


	public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
		
		Optional<Quiz> quiz =quizRepo.findById(id);
	
		if(!quiz.isPresent())
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Integer>(quizInterface.getScore(responses).getBody(),HttpStatus.OK);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) 	{
		Quiz quiz =quizRepo.findById(quizId).get();
		
		List<QuestionWrapper> questions=quizInterface.returnQuizQuestions(quiz.getSetOfQuestionId()).getBody();
		
		return new ResponseEntity<List<QuestionWrapper>>(questions,HttpStatus.OK);
		
	}
}
