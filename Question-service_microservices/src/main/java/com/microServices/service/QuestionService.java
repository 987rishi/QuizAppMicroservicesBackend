package com.microServices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microServices.model.Question;
import com.microServices.model.QuestionWrapper;
import com.microServices.model.Response;
import com.microServices.repo.QuestionRepo;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo repo;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		
	}

	public ResponseEntity<Question> saveQuestion(Question question) {
		try {
			return new ResponseEntity<>(repo.save(question),HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String cat) {
		try {
			return new ResponseEntity<>(repo.getQuestionsByCategory(cat),HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Integer>> getQuizQuestions(String category, Integer numQ) {
		
		return new ResponseEntity<List<Integer>>(repo.findByCategoryRandomQuestions(category, numQ),HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuesByIds(List<Integer> questionIds) {
		
		List<QuestionWrapper> questionsMasked=new ArrayList<>();
		
		for(Integer id:questionIds)
		{
			QuestionWrapper ques=new QuestionWrapper();
			
			Question question=repo.findById(id).get();
			ques.setId(id);
			ques.setOption1(question.getOption1());
			ques.setOption2(question.getOption2());
			ques.setOption3(question.getOption3());
			ques.setOption4(question.getOption4());
			ques.setQuestions(question.getQuestion());
			
			questionsMasked.add(ques);
			
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionsMasked,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(List<Response> responses) {
		
		int score=0;
		
		for(Response r:responses)
		{
			Question q=repo.findById(r.getId()).get();
			
			if(q.getQuestion().equals(r.getQuestion())&&q.getRightAnswer().equals(r.getUserAnswer()))
				score++;
		}
		
		return new ResponseEntity<Integer>(score,HttpStatus.OK);
	}
	
	
}
